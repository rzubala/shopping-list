package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingList

class ShoppingListViewModel(database: ShoppingDatabaseDao, application: Application): AndroidViewModel(application) {
    val shoppingList = database.getAllShoppingsList()
}
