package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao

class ShoppingListViewModel(database: ShoppingDatabaseDao, application: Application): AndroidViewModel(application) {

    val shoppingList = database.getAllShoppingsList()

    private val _navigateToShoppingDetails = MutableLiveData<Long>()
    val navigateToShoppingDetails: LiveData<Long>
        get() = _navigateToShoppingDetails

    fun onShoppingDetailClicked(id: Long) {
        _navigateToShoppingDetails.value = id
    }

    fun onShoppingDetailNavigated() {
        _navigateToShoppingDetails.value = null
    }
}
