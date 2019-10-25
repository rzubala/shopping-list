package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import kotlinx.coroutines.Job

class ShoppingDetailViewModel(shoppingDetailId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val viewModelJob = Job()

    private val shoppingDetail = MediatorLiveData<ShoppingDetail>()

    fun getShoppingDetail() = shoppingDetail

    init {
        shoppingDetail.addSource(database.getShoppingDetailWithId(shoppingDetailId), shoppingDetail::setValue)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}