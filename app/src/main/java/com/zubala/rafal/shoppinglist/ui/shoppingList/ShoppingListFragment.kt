package com.zubala.rafal.shoppinglist.ui.shoppingList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.zubala.rafal.shoppinglist.R
import com.zubala.rafal.shoppinglist.database.ShoppingDatabase
import com.zubala.rafal.shoppinglist.database.insertTestData
import com.zubala.rafal.shoppinglist.databinding.ShoppingListFragmentBinding
import com.zubala.rafal.shoppinglist.repository.CategoriesRepository

class ShoppingListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: ShoppingListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shopping_list_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ShoppingDatabase.getInstance(application).shoppingDatabaseDao

        val viewModelFactory = ShoppingListViewModelFactory(dataSource, application)

        val shoppingListViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingListViewModel::class.java)

        binding.shoppingListViewModel = shoppingListViewModel

        binding.lifecycleOwner = this

        val adapter = ShoppingListAdapter(ShoppingDetailListener {
            shoppingListViewModel.onShoppingDetailClicked(it)
        })

        shoppingListViewModel.navigateToShoppingDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    ShoppingListFragmentDirections.actionShoppingListFragmentToShoppingDetailFragment(
                        it
                    )
                )
                shoppingListViewModel.onShoppingDetailNavigated()
            }
        })

        binding.shoppingList.adapter = adapter

        shoppingListViewModel.shoppingList.observe(viewLifecycleOwner, Observer {
            adapter.submit(it)
        })

        insertTestData(application)

        return binding.root
    }
}
