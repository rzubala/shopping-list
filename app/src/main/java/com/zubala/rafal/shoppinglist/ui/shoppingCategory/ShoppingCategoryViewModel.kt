package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.domain.Category
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository

class ShoppingCategoryViewModel(private val shoppingDetailId: Long, private val shoppingCategoryId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val categoryRepository = CategoriesRepository(database)

    private val _categories = categoryRepository.categories

    // The external LiveData interface to the property is immutable, so only this class can modify
    val categories: LiveData<List<Category>>
        get() = _categories
}