package com.abdelrahman.amr.myshop.ui.signin
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdelrahman.amr.myshop.databinding.ActivitySigninBinding
import com.abdelrahman.amr.myshop.ui.mainactivity.HomeActivity
import com.abdelrahman.amr.myshop.utils.Resource
import com.abdelrahman.amr.myshop.utils.SupportFunctions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignInActivity : AppCompatActivity() {

    private  lateinit var binding :ActivitySigninBinding
    private lateinit var signInViewModel :SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        observeData()
        setListeners()


    }

private fun setListeners(){
    binding.btnLogin.setOnClickListener {
        showProgressBar()
        if (hasInternetConnection()){
            CoroutineScope(Dispatchers.Main).launch{
                login()

            }
        }
        else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }

    binding.ivBack.setOnClickListener {

     onBackPressed()
    }
}
    private fun login(){
        val email = binding.EtEmail.text.toString()
        val password = binding.EtPassword.text.toString()
        CoroutineScope(Dispatchers.Main).launch {
            signInViewModel.userLogin(email,password)
        }



    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.INVISIBLE
    }

    private fun hideProgressBar() {
        binding.progress.visibility = View.INVISIBLE
        binding.btnLogin.visibility = View.VISIBLE
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = application
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilites =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilites.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilites.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilites.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                else -> false

            }


        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false

                }
            }
            return false
        }
    }



    private fun observeData(){
        signInViewModel.userLogin.observe(this) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    SupportFunctions.startActivity(this, HomeActivity::class.java)

                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        hideProgressBar()
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }



}