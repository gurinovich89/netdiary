package com.netdiary.recipes.ui.recipes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.netdiary.data.Recipe
import com.netdiary.recipes.R
import com.netdiary.recipes.RecipesActivity
import kotlinx.android.synthetic.main.recipe_detail_fragment.*

class RecipeDetailFragment : Fragment() {

    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(iv_recipe, arguments?.getString(EXTRA_IV_TRANSITION_NAME))
        btn_back.setOnClickListener { v -> activity?.supportFragmentManager?.popBackStack() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(
                this, RecipeDetailViewModelFactory(
                    it.application, arguments?.getParcelable(EXTRA_RECIPE)
                )
            ).get(RecipeDetailViewModel::class.java)
            initObservers()
        }
    }

    private fun initObservers() {
        viewModel.title.observe(viewLifecycleOwner, Observer { title -> activity?.title = title })
        viewModel.recipeName.observe(
            viewLifecycleOwner,
            Observer { recipeName ->
                tv_title.text = recipeName
                tv_recipe_name.text = recipeName
            })
        viewModel.lowResolutionImageUrl.observe(viewLifecycleOwner,
            Observer { imageUrl ->
                Glide.with(iv_recipe.context)
                    .load(imageUrl)
                    .into(iv_recipe)
            })
        viewModel.highResolutionImageUrl.observe(viewLifecycleOwner,
            Observer { bitmap -> iv_recipe.setImageBitmap(bitmap) })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
        (activity as RecipesActivity).hideSystemUI()
    }

    companion object {
        const val EXTRA_RECIPE = "EXTRA_RECIPE"
        const val EXTRA_IV_TRANSITION_NAME = "EXTRA_IV_TRANSITION_NAME"
        fun newInstance(recipe: Recipe, ivTransitionName: String): RecipeDetailFragment {
            val params = Bundle()
            params.putParcelable(EXTRA_RECIPE, recipe)
            params.putString(EXTRA_IV_TRANSITION_NAME, ivTransitionName)
            return RecipeDetailFragment().apply { arguments = params }
        }
    }
}
