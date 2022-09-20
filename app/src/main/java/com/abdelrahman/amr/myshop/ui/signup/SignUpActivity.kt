package com.abdelrahman.amr.myshop.ui.signup

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.abdelrahman.amr.myshop.databinding.ActivitySignUpBinding
import com.abdelrahman.amr.myshop.db.PreferenceManager
import com.abdelrahman.amr.myshop.models.user.User
import com.abdelrahman.amr.myshop.ui.mainactivity.MainActivity
import com.abdelrahman.amr.myshop.ui.signin.SignInActivity
import com.abdelrahman.amr.myshop.utils.MyShopApplication
import com.abdelrahman.amr.myshop.utils.Resource
import com.abdelrahman.amr.myshop.utils.SupportFunctions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreference: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        setContentView(binding.root)
        setListeners()

    }

    private fun setListeners() {
        binding.btnRegister.setOnClickListener {
            if (hasInternetConnection()) {
                registerUser()
            } else {
                Toast.makeText(this, "no Internet Connection", Toast.LENGTH_LONG).show()
            }

        }

        binding.tvSingin.setOnClickListener {
            SupportFunctions.startActivity(this, SignInActivity::class.java)
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString()
        val email = binding.EtEmail.text.toString()
        val password = binding.EtPassword.text.toString()
        val phone = binding.EtPhone.text.toString()
        val user = User(email, "akfjhafa", name, password, phone)
        viewModel.registerUser(user)
        viewModel.userFlow.observe(this) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        hideProgressBar()
                        Log.d("abdelrahman", "error")
                        Toast.makeText(this@SignUpActivity, it, Toast.LENGTH_LONG).show()

                    }
                }
                is Resource.Loading -> {
                    Log.d("abdelrahman11122211", "loading")
                    Toast.makeText(this@SignUpActivity, "loading", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnRegister.visibility = View.GONE
                    binding.haveAccount.visibility = View.INVISIBLE
                    binding.tvSingin.visibility = View.INVISIBLE
                }
            }
        }

    }




private fun showProgressBar() {
    binding.progressBar.visibility = View.VISIBLE
    binding.btnRegister.visibility = View.GONE
    binding.haveAccount.visibility = View.INVISIBLE
    binding.tvSingin.visibility = View.INVISIBLE
}

private fun hideProgressBar() {
    binding.progressBar.visibility = View.INVISIBLE
    binding.btnRegister.visibility = View.VISIBLE
    binding.haveAccount.visibility = View.VISIBLE
    binding.tvSingin.visibility = View.VISIBLE
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
}
