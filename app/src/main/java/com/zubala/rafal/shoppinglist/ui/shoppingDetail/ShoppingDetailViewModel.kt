package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import android.util.Log
import androidx.lifecycle.*
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import com.zubala.rafal.shoppinglist.database.ShoppingDetailCategory
import com.zubala.rafal.shoppinglist.domain.Category
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingDetailViewModel(shoppingDetailId: Long, val dataSource: ShoppingDatabaseDao) : ViewModel() {
    val database = dataSource

    private val categoryRepository = CategoriesRepository(dataSource)
    private val _categories = MediatorLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

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
        _categories.addSource(categoryRepository.categories, _categories::setValue)
        _shoppingDetail.addSource(database.getShoppingDetailWithId(shoppingDetailId), _shoppingDetail::setValue)
        shoppingDetailCategories.addSource(database.getShoppingDetailCategories(shoppingDetailId), shoppingDetailCategories::setValue)
    }

    fun retrieveCategoryName(categoryId: Long): String {
        _categories.value?.let {list ->
            return list.find { categoryId == it.id }?.name?: ""
        }
        return ""
    }
}

class ShoppingCategoryRetriever(val nameRetriver: (categoryId: Long) -> String) {
    fun onNameRetrieve(id: Long) = nameRetriver(id)
}