package com.netdiary.recipes.ui.recipes

import androidx.paging.PositionalDataSource
import com.netdiary.data.Recipe
import com.netdiary.repository.RecipesRepository

internal class RecipesDataSource(private val recipesRepository: RecipesRepository) :
    PositionalDataSource<Recipe>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Recipe>) {
        val result =
            recipesRepository.getRecipes(params.requestedStartPosition, params.requestedLoadSize)
        callback.onResult(result, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Recipe>) {
        val result = recipesRepository.getRecipes(params.startPosition, params.loadSize)
        callback.onResult(result)
    }

    companion object {
        private val TAG = "RecipesDataSource"
    }
}
