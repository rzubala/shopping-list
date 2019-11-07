package com.zubala.rafal.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zubala.rafal.shoppinglist.domain.Category

@Entity(tableName = "shopping_category_table")
data class ShoppingCategory(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val name: String)

fun List<ShoppingCategory>.asDomainModel(): List<Category> {
    return map {Category(it.id, it.name)}
}