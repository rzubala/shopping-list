package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.ShoppingDetailCategory
import com.zubala.rafal.shoppinglist.domain.Category
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingCategoryViewModel(private val shoppingDetailCategoryId: Long, private val dataSource: ShoppingDatabaseDao) : ViewModel() {

    val database = dataSource

    private val categoryRepository = CategoriesRepository(database)

    private val categories = categoryRepository.categories

    private val _shoppingData = fetchData()
    val shoppingData: LiveData<MergedData>
        get() = _shoppingData

    fun updateCategory(categoryId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.updateShoppingCategory(shoppingDetailCategoryId, categoryId)
            }
        }
    }

    fun getCategorySelection(categoryId: Long): Int {
        val values = categories.value
        values?.let { list ->
            val category = list.find { it.id == categoryId }
            category.let {
                val index = list.indexOf(category)
                return if (index < 0) {
                    0
                } else {
                    index
                }
            }
        }
        return 0
    }

    private fun fetchData(): MediatorLiveData<MergedData> {
        val liveDataMerger = MediatorLiveData<MergedData>()
        liveDataMerger.addSource(categories) {
            it?.let { list ->
                liveDataMerger.value = CategoryData(list)
            }
        }
        liveDataMerger.addSource(database.getShoppingDetailCategoryWithId(shoppingDetailCategoryId)) {
            it?.let {
                liveDataMerger.value = ShoppingData(it)
            }
        }
        return liveDataMerger
    }
}

sealed class MergedData
data class CategoryData(val categories: List<Category>): MergedData()
data class ShoppingData(val shopping: ShoppingDetailCategory): MergedData()