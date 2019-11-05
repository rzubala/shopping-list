package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.network.CategoriesApi
import com.zubala.rafal.shoppinglist.network.CategoryProperty
import kotlinx.coroutines.launch

class ShoppingCategoryViewModel(private val shoppingDetailId: Long, private val shoppingCategoryId: Long, dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _categories = MutableLiveData<List<CategoryProperty>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val categories: LiveData<List<CategoryProperty>>
        get() = _categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            var categoriesDeffered = CategoriesApi.retrofitService.getCategories()
            try {
                _categories.value = categoriesDeffered.await()
                Log.i("ShoppingCategoryVM", _categories.value.toString())
            } catch (e: Exception) {
                Log.i("ShoppingCategoryVM", e.toString())
            }
        }
    }
}