package com.zubala.rafal.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_table")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "")
