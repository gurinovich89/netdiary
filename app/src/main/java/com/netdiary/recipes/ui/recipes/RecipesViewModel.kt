package com.netdiary.recipes.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.netdiary.data.Recipe
import com.netdiary.recipes.R
import com.netdiary.repository.RecipesRepository
import com.netdiary.utils.MainThreadExecutor
import java.util.concurrent.Executors

class RecipesViewModel(app: Application) : AndroidViewModel(app) {
    val title = MutableLiveData<String>()
    val recipesPagedList = MutableLiveData<PagedList<Recipe>>()

    init {
        title.postValue(app.getString(R.string.recipes_title))
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()
        val recipesDataSource = RecipesDataSource(RecipesRepository())
        val pagedList = PagedList.Builder(recipesDataSource, config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
        recipesPagedList.postValue(pagedList)
    }
}
