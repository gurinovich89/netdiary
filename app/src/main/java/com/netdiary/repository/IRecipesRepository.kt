package com.netdiary.repository

import com.netdiary.data.Recipe

interface IRecipesRepository {
    fun getRecipes(startIndex: Int, count: Int): List<Recipe>
}