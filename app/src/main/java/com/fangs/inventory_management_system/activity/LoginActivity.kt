package com.fangs.inventory_management_system.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fangs.inventory_management_system.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_signup.setOnClickListener{
            goToSignup()

        }

        //login
        btn_login.setOnClickListener {
            loginUser()

        }



    }

    private fun goToSignup() {
        Intent(this, SignupActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun loginUser(){
        val email = et_login_email.text.toString().trim{ it <= ' '}
        val password = et_login_password.text.toString().trim{ it <= ' '}

        val auth = FirebaseAuth.getInstance()


        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Login successfully!", Toast.LENGTH_SHORT).show()
                    Intent(applicationContext, MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }else{
                    Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }

    }

}