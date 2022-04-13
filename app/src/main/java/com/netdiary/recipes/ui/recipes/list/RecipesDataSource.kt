package com.netdiary.recipes.ui.recipes.list

import androidx.paging.PositionalDataSource
import com.netdiary.data.Recipe
import com.netdiary.usecase.GetRecipesUseCase

internal class RecipesDataSource(private val getRecipesUseCase: GetRecipesUseCase) :
    PositionalDataSource<Recipe>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Recipe>) {
        val result =
            getRecipesUseCase.getRecipes(params.requestedStartPosition, params.requestedLoadSize)
        callback.onResult(result, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Recipe>) {
        val result = getRecipesUseCase.getRecipes(params.startPosition, params.loadSize)
        callback.onResult(result)
    }

    companion object {
        private val TAG = "RecipesDataSource"
    }
}
