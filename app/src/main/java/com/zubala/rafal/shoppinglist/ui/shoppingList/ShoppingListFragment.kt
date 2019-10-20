package com.zubala.rafal.shoppinglist.ui.shoppingList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zubala.rafal.shoppinglist.R
import com.zubala.rafal.shoppinglist.database.ShoppingDatabase
import com.zubala.rafal.shoppinglist.databinding.ShoppingListFragmentBinding

class ShoppingListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: ShoppingListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shopping_list_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ShoppingDatabase.getInstance(application).shoppingDatabaseDao

        val viewModelFactory = ShoppingListViewModelFactory(dataSource, application)

        val shoppingListViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingListViewModel::class.java)

        binding.shoppingListViewModel = shoppingListViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
