package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.zubala.rafal.shoppinglist.domain.Category

class ShoppingCategoryAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val categories: List<Category>):
        ArrayAdapter<Category>(context, layoutResource, categories) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        view.text = categories[position].name
        return view
    }
}