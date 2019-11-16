package com.zubala.rafal.shoppinglist.database

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const private val insert = false

fun insertTestData(context: Context) {
    if (!insert) {
        return
    }

    CoroutineScope(Dispatchers.IO).launch {
        val dao = ShoppingDatabase.getInstance(context).shoppingDatabaseDao

        dao.deleteAllCategories()
        dao.deleteAllShoppingDetails()
        dao.deleteAllShoppingDetailCategories()

        val fruits = ShoppingCategory(1, "Fruits")
        val fruitsId = dao.insert(fruits)
        val vegetables = ShoppingCategory(2, "Vegetables")
        val vegetablesId = dao.insert(vegetables)

        for (i in 1..3) {
            val shopping = ShoppingDetail(name = "Biedronka $i")
            val id = dao.insert(shopping)

            val shoppingDetailCategory1 = ShoppingDetailCategory(shoppingDetailId = id, categoryId = fruitsId)
            dao.insert(shoppingDetailCategory1)

            val shoppingDetailCategory2 = ShoppingDetailCategory(shoppingDetailId = id, categoryId =  vegetablesId)
            dao.insert(shoppingDetailCategory2)
        }
    }
}