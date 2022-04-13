package com.netdiary.recipes.di

import com.netdiary.repository.IRecipesRepository
import com.netdiary.repository.MockRecipesRepository
import com.netdiary.usecase.GetRecipesUseCase

object Dependencies {
    const val DEFAULT_PAGE_SIZE = 10

    private val recipeRepository: IRecipesRepository = MockRecipesRepository()
    val getRecipesUseCase: GetRecipesUseCase = GetRecipesUseCase(recipeRepository)
}