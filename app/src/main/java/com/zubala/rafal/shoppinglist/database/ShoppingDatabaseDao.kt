package com.zubala.rafal.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingDatabaseDao {

    @Query("Select * from shopping_list_table order by id desc")
    fun getAllShoppingsList(): LiveData<List<ShoppingDetail>>

    @Query("SELECT * from shopping_list_table WHERE id = :key")
    fun getShoppingDetailWithId(key: Long): LiveData<ShoppingDetail>

    @Insert
    fun insert(shoppingDetail: ShoppingDetail)
}