package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zubala.rafal.shoppinglist.database.ShoppingDetail
import com.zubala.rafal.shoppinglist.database.ShoppingDetailCategory
import com.zubala.rafal.shoppinglist.databinding.ListItemDetailCategoryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingDetailAdapter : ListAdapter<CategoryItem.DetailCategoryItem, ShoppingDetailAdapter.ViewHolder>(ShoppingDetailCategoryDiffCallback()) {

    val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingDetailAdapter.ViewHolder {
        return ShoppingDetailAdapter.ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShoppingDetailAdapter.ViewHolder, position: Int) {
        val shoppingCategoryItem = getItem(position)
        holder.bind(shoppingCategoryItem.shoppingDetailCategory)
    }

    fun submit(categories: List<ShoppingDetailCategory>?) {
        Log.i("ShoppingDetailAdapter", "size: ${categories?.size ?: 0}")
        adapterScope.launch {
            val items = categories?.map { CategoryItem.DetailCategoryItem(it) }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    class ViewHolder(private val binding: ListItemDetailCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(shoppingDetailCategory: ShoppingDetailCategory) {
            binding.shoppingDetailCategory = shoppingDetailCategory
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemDetailCategoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ShoppingDetailCategoryDiffCallback : DiffUtil.ItemCallback<CategoryItem.DetailCategoryItem>() {
    override fun areItemsTheSame(oldItem: CategoryItem.DetailCategoryItem, newItem: CategoryItem.DetailCategoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryItem.DetailCategoryItem, newItem: CategoryItem.DetailCategoryItem): Boolean {
        return oldItem == newItem
    }
}

sealed class CategoryItem {
    data class DetailCategoryItem(val shoppingDetailCategory: ShoppingDetailCategory): CategoryItem() {
        override val id = shoppingDetailCategory.id
    }
    abstract val id: Long
}