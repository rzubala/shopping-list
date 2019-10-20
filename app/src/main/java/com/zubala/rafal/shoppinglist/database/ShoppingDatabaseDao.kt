package com.zubala.rafal.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ShoppingDatabaseDao {

    @Query("Select * from shopping_list_table order by id desc")
    fun getAllShoppingsList(): LiveData<List<ShoppingList>>
}