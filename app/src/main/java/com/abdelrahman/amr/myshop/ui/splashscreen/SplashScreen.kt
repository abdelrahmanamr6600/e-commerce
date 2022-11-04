package com.abdelrahman.amr.myshop.ui.splashscreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abdelrahman.amr.myshop.databinding.SplashActivityBinding

import com.abdelrahman.amr.myshop.db.PreferenceManager
import com.abdelrahman.amr.myshop.ui.mainactivity.HomeActivity
import com.abdelrahman.amr.myshop.ui.signin.SignInActivity
import com.abdelrahman.amr.myshop.ui.signup.SignUpActivity
import com.abdelrahman.amr.myshop.utils.Constants.Companion.IS_LOGIN
import com.abdelrahman.amr.myshop.utils.SupportFunctions
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
       setListeners()

        if(PreferenceManager(this).getBoolean(IS_LOGIN)) {
            runBlocking {
                delay(5000L)
                SupportFunctions.startActivity(this@SplashScreen, HomeActivity::class.java)
            }
        }
        else{
            binding.btnRegister.visibility = View.VISIBLE
            binding.btnSignIn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

        }


    }


    private fun setListeners(){
        binding.btnRegister.setOnClickListener {
            SupportFunctions.startActivity(this,SignUpActivity::class.java)
        }

        binding.btnSignIn.setOnClickListener {
            SupportFunctions.startActivity(this,SignInActivity::class.java)
        }
    }

}