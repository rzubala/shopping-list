package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao

class ShoppingCategoryViewModelFactory(private val shoppingDetailId: Long, private val shoppingCategoryId: Long, private val dataSource: ShoppingDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingCategoryViewModel::class.java)) {
            return ShoppingCategoryViewModel(shoppingDetailId, shoppingCategoryId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}