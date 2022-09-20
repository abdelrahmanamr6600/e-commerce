package com.abdelrahman.amr.myshop.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdelrahman.amr.myshop.databinding.SplashActivityBinding
import com.abdelrahman.amr.myshop.ui.signin.SignInActivity
import com.abdelrahman.amr.myshop.ui.signup.SignUpActivity
import com.abdelrahman.amr.myshop.utils.SupportFunctions

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
       setListeners()

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