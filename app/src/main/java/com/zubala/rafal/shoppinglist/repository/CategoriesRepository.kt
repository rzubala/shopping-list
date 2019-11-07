package com.zubala.rafal.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.zubala.rafal.shoppinglist.database.ShoppingDatabaseDao
import com.zubala.rafal.shoppinglist.database.asDomainModel
import com.zubala.rafal.shoppinglist.domain.Category
import com.zubala.rafal.shoppinglist.network.CategoriesApi
import com.zubala.rafal.shoppinglist.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesRepository (private val database: ShoppingDatabaseDao) {

    val categories: LiveData<List<Category>> = Transformations.map(database.getShoppingCategories()) {
        it.asDomainModel()
    }

    suspend fun refreshCategories() {
        withContext(Dispatchers.IO) {
            val categories = CategoriesApi.retrofitService.getCategories().await()
            database.insertAllCategories(*categories.asDatabaseModel())
        }
    }
}