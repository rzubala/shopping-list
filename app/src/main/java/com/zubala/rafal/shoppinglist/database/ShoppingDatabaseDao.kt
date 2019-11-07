package com.zubala.rafal.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDatabaseDao {

    @Query("Select * from shopping_list_table order by id desc")
    fun getAllShoppingsList(): LiveData<List<ShoppingDetail>>

    @Query("select * from shopping_detail_category where shopping_detail_id = :detailId order by id desc")
    fun getShoppingDetailCategories(detailId: Long): LiveData<List<ShoppingDetailCategory>>


    @Query("SELECT * from shopping_list_table WHERE id = :key")
    fun getShoppingDetailWithId(key: Long): LiveData<ShoppingDetail>

    @Insert
    fun insert(shoppingDetail: ShoppingDetail): Long

    @Insert
    fun insert(shoppingDetailCategory: ShoppingDetailCategory)

    @Delete
    fun deleteShoppingDetailCategories(vararg categories: ShoppingDetailCategory)

    @Query("delete from shopping_list_table")
    fun deleteAllShoppingDetailCategories()

    @Delete
    fun deleteShoppingDetails(vararg details: ShoppingDetail)

    @Query("delete from shopping_detail_category")
    fun deleteAllShoppingDetails()

    @Query("select * from shopping_category_table")
    fun getShoppingCategories(): LiveData<List<ShoppingCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(vararg categories: ShoppingCategory)
}