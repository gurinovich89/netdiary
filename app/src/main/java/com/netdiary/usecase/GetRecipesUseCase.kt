package com.netdiary.usecase

import com.netdiary.data.Recipe
import com.netdiary.repository.IRecipesRepository

class GetRecipesUseCase(private val recipesRepository: IRecipesRepository) {
    fun getRecipes(startIndex: Int, count: Int): List<Recipe> {
        return recipesRepository.getRecipes(startIndex, count)
    }
}