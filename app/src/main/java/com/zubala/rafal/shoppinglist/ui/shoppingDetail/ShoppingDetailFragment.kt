package com.zubala.rafal.shoppinglist.ui.shoppingDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zubala.rafal.shoppinglist.R
import com.zubala.rafal.shoppinglist.databinding.ShoppingDetailFragmentBinding

class ShoppingDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: ShoppingDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shopping_detail_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = ShoppingDetailFragmentArgs.fromBundle(arguments!!)

        Toast.makeText(application, "Id: ${arguments.shoppingDetailKey}", Toast.LENGTH_LONG).show()

        return binding.root
    }
}