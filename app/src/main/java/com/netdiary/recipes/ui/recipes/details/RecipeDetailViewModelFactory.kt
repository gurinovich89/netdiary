package com.netdiary.recipes.ui.recipes.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.netdiary.data.Recipe

class RecipeDetailViewModelFactory(
    private val app: Application,
    private val recipe: Recipe?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeDetailViewModel(app, recipe) as T
    }
}
