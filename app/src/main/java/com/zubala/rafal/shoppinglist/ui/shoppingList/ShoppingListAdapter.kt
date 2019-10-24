package com.zubala.rafal.shoppinglist.ui.shoppingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import com.zubala.rafal.shoppinglist.databinding.ListItemShoppingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingListAdapter(val shoppingDetailListener: ShoppingDetailListener) : ListAdapter<DataItem.ShoppingDetailItem, ShoppingListAdapter.ViewHolder>(ShoppingListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingListItem = getItem(position)
        holder.bind(shoppingListItem.shoppingDetail, shoppingDetailListener)
    }

    class ViewHolder(private val binding: ListItemShoppingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(shoppingDetail: ShoppingDetail, clickListener: ShoppingDetailListener) {
            binding.shoppingDetail = shoppingDetail
            binding.clickListener = clickListener
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

    fun submit(detail: List<ShoppingDetail>?) {
        adapterScope.launch {
            val items = detail?.map { DataItem.ShoppingDetailItem(it) }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
}

class ShoppingListDiffCallback : DiffUtil.ItemCallback<DataItem.ShoppingDetailItem>() {
    override fun areItemsTheSame(
        oldItem: DataItem.ShoppingDetailItem,
        newItem: DataItem.ShoppingDetailItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataItem.ShoppingDetailItem,
        newItem: DataItem.ShoppingDetailItem
    ): Boolean {
        return oldItem == newItem
    }
}

class ShoppingDetailListener(val clickListener: (shoppingDetailId: Long) -> Unit) {
    fun onShoppingDetailClicked(shoppingDetail: ShoppingDetail) = clickListener(shoppingDetail.id)
}

sealed class DataItem {
    data class ShoppingDetailItem(val shoppingDetail: ShoppingDetail): DataItem() {
        override val id = shoppingDetail.id
    }
    abstract val id: Long
}