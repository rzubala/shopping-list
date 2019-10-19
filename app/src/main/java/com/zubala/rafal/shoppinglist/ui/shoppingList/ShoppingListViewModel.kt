package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao

class ShoppingListViewModel(
    dataSource: ShoppingDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
}
