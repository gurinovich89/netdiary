package com.netdiary.recipes.ui.recipes

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.netdiary.data.Recipe
import com.netdiary.recipes.R


class RecipesAdapter(context: Context, val itemClick: (Recipe, Int, View) -> Unit) :
    PagedListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffItemCallback()) {

    private val progressDrawableStrokeWidth: Float
    private val progressDrawableRadius: Float

    init {
        val displayMetrics = context.resources.displayMetrics
        progressDrawableStrokeWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, displayMetrics)
        progressDrawableRadius =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, displayMetrics)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    inner class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val ivRecipe: ImageView
        private val tvRecipeName: TextView
        private val frameRecipe: View
        private val circularProgressDrawable: CircularProgressDrawable

        init {
            ivRecipe = view.findViewById(R.id.iv_recipe)
            tvRecipeName = view.findViewById(R.id.tv_recipe_name)
            frameRecipe = view.findViewById(R.id.frame_recipe)
            circularProgressDrawable = CircularProgressDrawable(ivRecipe.context)
            circularProgressDrawable.strokeWidth = progressDrawableStrokeWidth
            circularProgressDrawable.centerRadius = progressDrawableRadius
            circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(
                    ivRecipe.context,
                    R.color.colorAccent
                )
            )
            frameRecipe.setOnClickListener { v ->
                getItem(adapterPosition)?.let { itemClick.invoke(it, adapterPosition, ivRecipe) }
            }
        }

        fun bind(position: Int, recipe: Recipe?) {
            ViewCompat.setTransitionName(ivRecipe, "recipe_image_$position")
            circularProgressDrawable.start()
            recipe?.let {
                tvRecipeName.text = it.name
                Glide.with(ivRecipe.context)
                    .load(it.imageUrl)
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(ivRecipe)
            }
        }
    }

    class RecipeDiffItemCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }
    }
}