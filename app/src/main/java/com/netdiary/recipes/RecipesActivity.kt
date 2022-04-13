package com.netdiary.recipes

import android.os.Bundle
import com.netdiary.recipes.ui.recipes.list.RecipesFragment

class RecipesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecipesFragment.newInstance())
                .commitNow()
        }
    }
}
