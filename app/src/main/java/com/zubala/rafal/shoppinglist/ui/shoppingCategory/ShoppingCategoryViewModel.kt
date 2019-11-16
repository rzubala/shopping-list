package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingCategoryViewModel(private val shoppingDetailId: Long, private val dataSource: ShoppingDatabaseDao) : ViewModel() {

    val database = dataSource

    private val categoryRepository = CategoriesRepository(database)

    val categories = categoryRepository.categories

    fun updateCategory(categoryId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.updateShoppingCategory(shoppingDetailId, categoryId)
            }
        }
    }
}