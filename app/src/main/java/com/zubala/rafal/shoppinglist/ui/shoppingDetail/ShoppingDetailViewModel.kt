package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import com.zubala.rafal.shoppinglist.database.ShoppingDetailCategory

class ShoppingDetailViewModel(shoppingDetailId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val _shoppingDetail = MediatorLiveData<ShoppingDetail>()
    fun getShoppingDetail() = _shoppingDetail

    val shoppingDetailCategories = MediatorLiveData<List<ShoppingDetailCategory>>()

    init {
        _shoppingDetail.addSource(database.getShoppingDetailWithId(shoppingDetailId), _shoppingDetail::setValue)
        shoppingDetailCategories.addSource(database.getShoppingDetailCategories(shoppingDetailId), shoppingDetailCategories::setValue)
    }
}