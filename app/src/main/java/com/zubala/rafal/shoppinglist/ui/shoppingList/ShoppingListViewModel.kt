package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.app.Application
import androidx.lifecycle.*
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository
import kotlinx.coroutines.launch

class ShoppingListViewModel(database: ShoppingDatabaseDao, application: Application): AndroidViewModel(application) {

    val shoppingList = database.getAllShoppingsList()

    private val _navigateToShoppingDetails = MutableLiveData<Long>()
    val navigateToShoppingDetails: LiveData<Long>
        get() = _navigateToShoppingDetails

    private val categoryRepository = CategoriesRepository(database)

    init {
        viewModelScope.launch {
            categoryRepository.refreshCategories()
        }
    }

    fun onShoppingDetailClicked(id: Long) {
        _navigateToShoppingDetails.value = id
    }

    fun onShoppingDetailNavigated() {
        _navigateToShoppingDetails.value = null
    }
}
