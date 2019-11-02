package com.zubala.rafal.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shopping_list_table")
data class ShoppingDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var created: Date,
    var closed: Boolean = false)
