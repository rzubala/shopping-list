package com.zubala.rafal.shoppinglist.database

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun insertTestData(context: Context) {

    CoroutineScope(Dispatchers.IO).launch {
        val dao = ShoppingDatabase.getInstance(context).shoppingDatabaseDao

        for (i in 1..10) {
            val shopping = ShoppingDetail(name = "Biedronka $i")
            dao.insert(shopping)
        }
    }
}