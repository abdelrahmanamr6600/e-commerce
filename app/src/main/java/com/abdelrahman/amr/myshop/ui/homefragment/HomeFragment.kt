package com.abdelrahman.amr.myshop.ui.homefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdelrahman.amr.myshop.HomeAdapter
import com.abdelrahman.amr.myshop.databinding.FragmentHomeBinding
import com.abdelrahman.amr.myshop.utils.Resource
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var productsAdapter: HomeAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)




        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        CoroutineScope(Dispatchers.Main).launch{
            delay(10000L)
            homeViewModel.getHomeData()
        }



        productsAdapter = HomeAdapter()

        homeViewModel.dataFlow.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                      binding.rvProducts.visibility = View.VISIBLE
                    binding.rvProducts.adapter = productsAdapter
                    productsAdapter.differ.submitList(it.data!!.data!!.products)
                    Glide.with(this).load(it.data.data!!.ad.toString()).into(binding.ivAd)
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }

                is Resource.Loading ->{

                }
            }



        }
        return binding.root
    }










//    private fun setListLoader() {
//        Log.d("loading","Loading")
//        binding.rvProducts.visibility= View.GONE
//        binding.root.isFocusableInTouchMode = true
//        binding.root.requestFocus()
//        binding.shimmerFrameLayout.visibility=View.VISIBLE
//        val builder = Shimmer.AlphaHighlightBuilder()
//        builder.setDirection(Shimmer.Direction.TOP_TO_BOTTOM)
//        builder.setClipToChildren(true)
//        binding.shimmerFrameLayout.shouldDelayChildPressedState()
//        binding.shimmerFrameLayout.setShimmer(builder.build())
//        binding.shimmerFrameLayout.startShimmer()
//    }
//
//    private fun  stopListLoader()
//    {
//        binding.rvProducts.visibility= View.VISIBLE
//        binding.shimmerFrameLayout.visibility=View.INVISIBLE
//        binding.shimmerFrameLayout.stopShimmer()
//    }
}