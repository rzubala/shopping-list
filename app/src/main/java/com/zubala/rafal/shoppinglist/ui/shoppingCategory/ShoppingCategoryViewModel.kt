package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import androidx.lifecycle.ViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao

class ShoppingCategoryViewModel(private val shoppingDetailId: Long, private val shoppingCategoryId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource
}