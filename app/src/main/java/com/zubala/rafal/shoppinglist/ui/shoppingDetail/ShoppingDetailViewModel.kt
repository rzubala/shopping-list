package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingDetailViewModel(shoppingDetailId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val shoppingDetail = MediatorLiveData<ShoppingDetail>()

    fun getShoppingDetail() = shoppingDetail

    init {
        shoppingDetail.addSource(database.getShoppingDetailWithId(shoppingDetailId), shoppingDetail::setValue)
    }
}