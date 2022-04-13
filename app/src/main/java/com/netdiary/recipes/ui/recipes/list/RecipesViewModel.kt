package com.netdiary.recipes.ui.recipes.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.netdiary.data.Recipe
import com.netdiary.recipes.R
import com.netdiary.recipes.di.Dependencies
import com.netdiary.utils.MainThreadExecutor
import java.util.concurrent.Executors

class RecipesViewModel(app: Application) : AndroidViewModel(app) {
    val title = MutableLiveData<String>()
    val recipesPagedList = MutableLiveData<PagedList<Recipe>>()

    init {
        title.postValue(app.getString(R.string.recipes_title))
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Dependencies.DEFAULT_PAGE_SIZE)
            .build()
        val recipesDataSource = RecipesDataSource(Dependencies.getRecipesUseCase)
        val pagedList = PagedList.Builder(recipesDataSource, config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
        recipesPagedList.postValue(pagedList)
    }
}
