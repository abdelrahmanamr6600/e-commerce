package com.abdelrahman.amr.myshop

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abdelrahman.amr.myshop.databinding.ProductItemContainerBinding
import com.abdelrahman.amr.myshop.models.home.Product
import com.bumptech.glide.Glide

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(var binding: ProductItemContainerBinding):ViewHolder(binding.root) {
        fun setAnalyticData(product: Product) {
            Glide.with(binding.root.context).load(product.image).into(binding.ivProductImage)
            binding.tvProductName.text = product.name
            if (product.discount!! >0){
                binding.tvOldProductPrice.paintFlags =  binding.tvOldProductPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            }
            binding.tvDisccount.text ="${product.discount.toString()} OFF"

            binding.tvNewProductPrice.text = "EGP${product.price.toString()}"
            binding.tvOldProductPrice.text = "EGP${product.oldPrice.toString()}"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ProductItemContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    private val differCallback  = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this@HomeAdapter,differCallback)

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.setAnalyticData(differ.currentList[position])
    }
}