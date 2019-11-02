package com.zubala.rafal.shoppinglist.database

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shopping_list_table")
data class ShoppingDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var created: Long = Date().time,
    var closed: Boolean = false)
