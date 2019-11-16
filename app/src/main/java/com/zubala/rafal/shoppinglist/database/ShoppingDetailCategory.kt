package com.zubala.rafal.shoppinglist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_detail_category",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ShoppingDetail::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("shopping_detail_id")),
        ForeignKey(
            entity = ShoppingCategory::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"))
    )
)
data class ShoppingDetailCategory(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "shopping_detail_id") val shoppingDetailId: Long,
    @ColumnInfo(name = "category_id") val categoryId: Long)