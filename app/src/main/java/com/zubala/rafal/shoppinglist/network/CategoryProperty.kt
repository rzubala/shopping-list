package com.zubala.rafal.shoppinglist.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryProperty(val id: String, val name: String) : Parcelable