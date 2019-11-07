package com.zubala.rafal.shoppinglist.network

import android.os.Parcelable
import com.zubala.rafal.shoppinglist.database.ShoppingCategory
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryProperty(val id: Long, val name: String) : Parcelable

fun List<CategoryProperty>.asDatabaseModel(): Array<ShoppingCategory> {
    return map {
        ShoppingCategory(it.id, it.name)
    }.toTypedArray()
}