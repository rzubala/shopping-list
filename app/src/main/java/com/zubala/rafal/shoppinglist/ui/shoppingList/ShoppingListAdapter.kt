package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zubala.rafal.shoppinglist.database.ShoppingList
import com.zubala.rafal.shoppinglist.databinding.ListItemShoppingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingListAdapter : ListAdapter<DataItem.ShoppingListItem, ShoppingListAdapter.ViewHolder>(ShoppingListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingListItem = getItem(position)
        holder.bind(shoppingListItem.shoppingList)
    }

    class ViewHolder(private val binding: ListItemShoppingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(shoppingList: ShoppingList) {
            binding.shoppingList = shoppingList
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemShoppingBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun submit(list: List<ShoppingList>?) {
        adapterScope.launch {
            val items = list?.map { DataItem.ShoppingListItem(it) }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
}

class ShoppingListDiffCallback : DiffUtil.ItemCallback<DataItem.ShoppingListItem>() {
    override fun areItemsTheSame(
        oldItem: DataItem.ShoppingListItem,
        newItem: DataItem.ShoppingListItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataItem.ShoppingListItem,
        newItem: DataItem.ShoppingListItem
    ): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem {
    data class ShoppingListItem(val shoppingList: ShoppingList): DataItem() {
        override val id = shoppingList.id
    }
    abstract val id: Long
}