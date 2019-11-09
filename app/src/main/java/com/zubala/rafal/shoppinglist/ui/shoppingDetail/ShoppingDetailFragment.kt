package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.zubala.rafal.shoppinglist.R
import com.zubala.rafal.shoppinglist.database.ShoppingDatabase
import com.zubala.rafal.shoppinglist.databinding.ShoppingDetailFragmentBinding

class ShoppingDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: ShoppingDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shopping_detail_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = ShoppingDetailFragmentArgs.fromBundle(arguments!!)

        val dataSource = ShoppingDatabase.getInstance(application).shoppingDatabaseDao
        val viewModelFactory = ShoppingDetailViewModelFactory(arguments.shoppingDetailKey, dataSource)

        val shoppingDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShoppingDetailViewModel::class.java)

        binding.shoppingDetailViewModel = shoppingDetailViewModel

        val shoppingDetailCategoryAdapter = ShoppingDetailAdapter(ShoppingDetailCategoryListener { shoppingDetailCategoryId ->
            shoppingDetailViewModel.onShoppingDetailClicked(shoppingDetailCategoryId)
        })

        shoppingDetailViewModel.navigateToShoppingDetailCategory.observe(this, Observer {
            it?.let {
                this.findNavController().navigate(
                    ShoppingDetailFragmentDirections.actionShoppingDetailFragmentToShoppingDetailCategoryFragment(
                        it, 0L
                    )
                )
                shoppingDetailViewModel.onShoppingDetailNavigated()
            }
        })

        binding.addNewCategory.setOnClickListener {
            this.findNavController().navigate(ShoppingDetailFragmentDirections.actionShoppingDetailFragmentToShoppingDetailCategoryFragment(
                arguments.shoppingDetailKey, 0L
            ))
        }

        binding.detailCategoryList.adapter = shoppingDetailCategoryAdapter

        binding.lifecycleOwner = this

        shoppingDetailViewModel.shoppingDetailCategories.observe(viewLifecycleOwner, Observer {
            shoppingDetailCategoryAdapter.submit(it)
        })

        return binding.root
    }
}