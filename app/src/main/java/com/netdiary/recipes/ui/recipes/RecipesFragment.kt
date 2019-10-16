package com.netdiary.recipes.ui.recipes

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.netdiary.data.Recipe
import com.netdiary.recipes.R
import com.netdiary.recipes.RecipesActivity
import com.netdiary.recipes.ui.recipes.details.RecipeDetailFragment
import kotlinx.android.synthetic.main.recipes_fragment.*


class RecipesFragment : Fragment() {

    private lateinit var viewModel: RecipesViewModel
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.recipes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let {
            recipesAdapter = RecipesAdapter(it) { recipe, position, itemView ->
                openDetailRecipe(
                    recipe,
                    position,
                    itemView
                )
            }
            rv_recipes.layoutManager = LinearLayoutManager(it)
            rv_recipes.addItemDecoration(DividerItemDecoration(it, LinearLayoutManager.VERTICAL))
            rv_recipes.adapter = recipesAdapter
            viewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
            initObservers()
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as RecipesActivity).showSystemUI()
    }

    fun initObservers() {
        viewModel.title.observe(viewLifecycleOwner, Observer { title -> activity?.title = title })
        viewModel.recipesPagedList.observe(
            viewLifecycleOwner,
            Observer { recipesList -> recipesAdapter.submitList(recipesList) })
    }

    private fun openDetailRecipe(recipe: Recipe, position: Int, ivRecipe: View) {
        activity?.let {
            val ivTransitionName = "recipe_image_$position"
            val detailFragment =
                RecipeDetailFragment.newInstance(recipe, ivTransitionName)
            exitTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.recipes_list_exit_transition)
            val transition = TransitionInflater.from(context)
                .inflateTransition(R.transition.recipe_shared_image_transition)
            detailFragment.sharedElementEnterTransition = transition
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack(null)
                .addSharedElement(ivRecipe, ivTransitionName)
                .commit()

        }
    }

    companion object {
        fun newInstance() = RecipesFragment()
    }
}
