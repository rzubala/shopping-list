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

        dao.deleteAllShoppingDetails()
        dao.deleteAllShoppingDetailCategories()

        for (i in 1..3) {
            val shopping = ShoppingDetail(name = "Biedronka $i")
            val id = dao.insert(shopping)

            val shoppingDetailCategory1 = ShoppingDetailCategory(category = "Owoce $i", shoppingDetailId = id)
            dao.insert(shoppingDetailCategory1)

            val shoppingDetailCategory2 = ShoppingDetailCategory(category = "Warzywa $i", shoppingDetailId = id)
            dao.insert(shoppingDetailCategory2)
        }
    }
}