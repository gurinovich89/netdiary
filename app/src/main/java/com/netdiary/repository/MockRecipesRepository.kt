package com.netdiary.repository

import com.netdiary.data.Recipe
import java.util.ArrayList

class MockRecipesRepository : IRecipesRepository {

    override fun getRecipes(startIndex: Int, count: Int): List<Recipe> {
        val result = ArrayList<Recipe>(count)
        for (i in startIndex until startIndex + count) {
            result.add(recipes[i % recipes.size])
        }
        return result
    }

    companion object {
        private val recipeImages = arrayOf(
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/11985403.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/11986497.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/11988733.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/11989088.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/12153487.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/12167354.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/12231705.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/12362599.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/12369258.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/preview/12409611.jpg"
        )
        private val highResolutionRecipeImages = arrayOf(
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11985403.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11986497.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11988733.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11989088.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12153487.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12167354.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12231705.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12362599.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12369258.jpg",
            "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12409611.jpg"
        )
        val recipes = Array<Recipe>(10) { i ->
            Recipe(
                i.toLong(),
                "Recipe_$i",
                recipeImages[i % recipeImages.size],
                highResolutionRecipeImages[i % highResolutionRecipeImages.size]
            )
        }
    }
}