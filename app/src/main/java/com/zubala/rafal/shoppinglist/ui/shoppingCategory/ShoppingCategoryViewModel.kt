package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import androidx.lifecycle.ViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository

class ShoppingCategoryViewModel(private val shoppingDetailId: Long, private val shoppingCategoryId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val categoryRepository = CategoriesRepository(database)

    val categories = categoryRepository.categories

}