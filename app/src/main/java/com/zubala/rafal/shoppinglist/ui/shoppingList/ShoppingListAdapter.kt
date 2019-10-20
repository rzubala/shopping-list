package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zubala.rafal.shoppinglist.database.ShoppingList

class ShoppingListAdapter : ListAdapter<DataItem.ShoppingListItem, RecyclerView.ViewHolder>(ShoppingListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class ShoppingListDiffCallback : DiffUtil.ItemCallback<DataItem.ShoppingListItem>() {
    override fun areItemsTheSame(
        oldItem: DataItem.ShoppingListItem,
        newItem: DataItem.ShoppingListItem
    ): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areContentsTheSame(
        oldItem: DataItem.ShoppingListItem,
        newItem: DataItem.ShoppingListItem
    ): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

sealed class DataItem {
    data class ShoppingListItem(val shoppingList: ShoppingList): DataItem() {
        override val id = shoppingList.id
    }
    abstract val id: Long
}