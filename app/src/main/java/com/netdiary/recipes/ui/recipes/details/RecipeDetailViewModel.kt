package com.netdiary.recipes.ui.recipes.details

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.netdiary.data.Recipe
import com.netdiary.recipes.R


class RecipeDetailViewModel(
    private val app: Application,
    private val recipe: Recipe?
) : AndroidViewModel(app) {
    val title = MutableLiveData<String>()
    val recipeName = MutableLiveData<String>()
    val lowResolutionImageUrl = MutableLiveData<String>()
    val highResolutionImageUrl = MutableLiveData<Bitmap>()

    fun onStart() {
        recipe?.let {
            title.postValue(app.getString(R.string.recipe_detail_title))
            recipeName.postValue(it.name)
            lowResolutionImageUrl.postValue(it.imageUrl)
            downloadHighResolutionImage(it)
        }
    }

    private fun downloadHighResolutionImage(recipe: Recipe) {
        Glide.with(app)
            .asBitmap()
            .load(recipe.highResolutionImageUrl)
            .into(object : CustomTarget<Bitmap>(1920, 1920) {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    highResolutionImageUrl.postValue(resource)
                }
            })

    }

    override fun onCleared() {
        super.onCleared()
        //TODO cancel download high resolution image
    }
}
