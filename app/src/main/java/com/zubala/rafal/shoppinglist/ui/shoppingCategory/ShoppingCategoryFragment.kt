package com.zubala.rafal.shoppinglist.ui.shoppingCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.zubala.rafal.shoppinglist.R
import com.zubala.rafal.shoppinglist.database.ShoppingDatabase
import com.zubala.rafal.shoppinglist.databinding.ShoppingCategoryFragmentBinding

class ShoppingCategoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ShoppingCategoryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shopping_category_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = ShoppingCategoryFragmentArgs.fromBundle(arguments!!)

        val dataSource = ShoppingDatabase.getInstance(application).shoppingDatabaseDao
        val viewModelFactory = ShoppingCategoryViewModelFactory(arguments.shoppingDetailId, arguments.shoppingCategoryId, dataSource)

        val shoppingCategoryViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingCategoryViewModel::class.java)

        binding.shoppingCategoryViewModel = shoppingCategoryViewModel

        binding.lifecycleOwner = this


        return binding.root
    }
}