package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zubala.rafal.shoppinglist.R
import com.zubala.rafal.shoppinglist.database.ShoppingDatabase
import com.zubala.rafal.shoppinglist.databinding.ShoppingCategoryFragmentBinding
import com.zubala.rafal.shoppinglist.domain.Category

class ShoppingCategoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ShoppingCategoryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shopping_category_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = ShoppingCategoryFragmentArgs.fromBundle(arguments!!)

        val dataSource = ShoppingDatabase.getInstance(application).shoppingDatabaseDao
        val viewModelFactory = ShoppingCategoryViewModelFactory(arguments.shoppingDetailId, dataSource)

        val shoppingCategoryViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingCategoryViewModel::class.java)

        binding.shoppingCategoryViewModel = shoppingCategoryViewModel

        shoppingCategoryViewModel.shoppingData.observe(this, object : Observer<MergedShoppingCategoryData> {
            override fun onChanged(mergedData: MergedShoppingCategoryData?) {
                when (mergedData) {
                    is ShoppingData -> shoppingCategoryViewModel.shopping = mergedData.shopping
                    is CategoryData -> shoppingCategoryViewModel.categories = mergedData.categories
                }
                shoppingCategoryViewModel.shopping?.let {
                    shoppingCategoryViewModel.categories?.let {
                        val adapter = ShoppingCategoryAdapter(this@ShoppingCategoryFragment.activity!!, android.R.layout.simple_spinner_item, shoppingCategoryViewModel.categories!!)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.shoppingCategory.adapter = adapter
                        binding.shoppingCategory.setSelection(shoppingCategoryViewModel.getCategorySelection(shoppingCategoryViewModel.shopping!!.categoryId))
                        shoppingCategoryViewModel.shoppingData.removeObserver(this)
                    }
                }
            }
        })

        binding.lifecycleOwner = this

        binding.shoppingCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.getItemAtPosition(position) as Category
                item?.let {
                    shoppingCategoryViewModel.updateCategory(item.id)
                }
            }
        }

        return binding.root
    }
}