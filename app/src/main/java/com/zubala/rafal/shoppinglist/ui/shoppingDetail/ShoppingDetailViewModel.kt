package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import com.zubala.rafal.shoppinglist.database.ShoppingDetailCategory

class ShoppingDetailViewModel(shoppingDetailId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val _shoppingDetail = MediatorLiveData<ShoppingDetail>()
    fun getShoppingDetail() = _shoppingDetail

    private val _navigateToShoppingDetailCategory = MutableLiveData<Long>()
    val navigateToShoppingDetailCategory: LiveData<Long>
        get() = _navigateToShoppingDetailCategory

    fun onShoppingDetailClicked(shoppingDetailCategoryId: Long) {
        _navigateToShoppingDetailCategory.value = shoppingDetailCategoryId
    }

    fun onShoppingDetailNavigated() {
        _navigateToShoppingDetailCategory.value = null
    }

    val shoppingDetailCategories = MediatorLiveData<List<ShoppingDetailCategory>>()

    init {
        _shoppingDetail.addSource(database.getShoppingDetailWithId(shoppingDetailId), _shoppingDetail::setValue)
        shoppingDetailCategories.addSource(database.getShoppingDetailCategories(shoppingDetailId), shoppingDetailCategories::setValue)
    }
}