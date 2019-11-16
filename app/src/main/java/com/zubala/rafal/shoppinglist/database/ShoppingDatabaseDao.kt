package com.zubala.rafal.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDatabaseDao {

    @Query("Select * from shopping_list_table order by id desc")
    fun getAllShoppingsList(): LiveData<List<ShoppingDetail>>

    @Query("Select * from shopping_detail_category where shopping_detail_id = :detailId order by id desc")
    fun getShoppingDetailCategories(detailId: Long): LiveData<List<ShoppingDetailCategory>>


    @Query("Select * from shopping_list_table WHERE id = :key")
    fun getShoppingDetailWithId(key: Long): LiveData<ShoppingDetail>

    @Insert
    fun insert(shoppingDetail: ShoppingDetail): Long

    @Insert
    fun insert(shoppingDetailCategory: ShoppingDetailCategory)

    @Delete
    fun deleteShoppingDetailCategories(vararg categories: ShoppingDetailCategory)

    @Query("Update shopping_detail_category set category_id = :categoryId where id = :shoppingCategoryId")
    fun updateShoppingCategory(shoppingCategoryId: Long, categoryId: Long)

    @Query("Delete from shopping_list_table")
    fun deleteAllShoppingDetailCategories()

    @Delete
    fun deleteShoppingDetails(vararg details: ShoppingDetail)

    @Query("Delete from shopping_detail_category")
    fun deleteAllShoppingDetails()

    @Query("Select * from shopping_category_table")
    fun getShoppingCategories(): LiveData<List<ShoppingCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(vararg categories: ShoppingCategory)

    @Insert
    fun insert(shoppingCategory: ShoppingCategory): Long

    @Query("Delete from shopping_category_table")
    fun deleteAllCategories()
}