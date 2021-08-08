package com.fangs.inventory_management_system.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fangs.inventory_management_system.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

            TextUtils.isEmpty(et_username.text.toString().trim {it <= ' '}) -> {
                Toast.makeText(this, "username cannot be empty", Toast.LENGTH_SHORT).show()
                false
            }

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
            val username = et_username.text.toString().trim {it <= ' '}
            val email = et_signup_email.text.toString().trim { it <= ' '}
            val password = et_signup_password.text.toString().trim {it <= ' '}

            val authRef = FirebaseAuth.getInstance()

            authRef.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful){

                        //get currentUser
                        val user : FirebaseUser = task.result!!.user!!

                        Toast.makeText(this, "Registration was successful! with UID ${user.uid}", Toast.LENGTH_LONG).show()

                        //open firestore db and create doc for the user
                        val db = Firebase.firestore
                        db.collection("user").document(user.uid).collection("profile").document("info").set(
                            hashMapOf(
                                "username" to username
                            )
                        )



                        goToLogin()
                        finish()


                    }else{

                        Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }

                }




        }


    }


}