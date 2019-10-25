package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.zubala.rafal.shoppinglist.database.ShoppingDetail

@BindingAdapter("shopingDetailName")
fun TextView.setShoppingDetailName(shoppingDetail: ShoppingDetail?) {
    shoppingDetail?.let {
        text = "${it.name} with id ${it.id}"
    }
}