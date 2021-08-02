package com.fangs.inventory_management_system.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.fangs.inventory_management_system.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        tv_login.setOnClickListener {
            goToLogin()
        }
        
        //register the user
        btn_signup.setOnClickListener {
            
            createUser()
            
        }
    }

    private fun goToLogin() {
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
        }
    }


    //check if the inputs are valid
    private fun validateUserInfo() : Boolean {
        return when{
            TextUtils.isEmpty(et_signup_email.text.toString().trim {it <= ' '}) -> {
                Toast.makeText(this, "email cannot be empty", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(et_signup_password.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "password cannot be empty", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

    private fun createUser(){

        if(validateUserInfo()){

            //remove empty spaces
            val email = et_signup_email.text.toString().trim { it <= ' '}
            val password = et_signup_password.text.toString().trim {it <= ' '}

            val authRef = FirebaseAuth.getInstance()


            authRef.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful){

                        Toast.makeText(this, "Registration was successful!", Toast.LENGTH_SHORT).show()
                        goToLogin()
                        finish()


                    }else{

                        Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }

                }




        }


    }


}