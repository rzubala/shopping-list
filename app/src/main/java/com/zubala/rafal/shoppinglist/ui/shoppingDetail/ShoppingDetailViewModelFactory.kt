package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao

class ShoppingDetailViewModelFactory(private val shoppingDetailId: Long, private val dataSource: ShoppingDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingDetailViewModel::class.java)) {
            return ShoppingDetailViewModel(shoppingDetailId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}