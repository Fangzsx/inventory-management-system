package com.fangs.inventory_management_system.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fangs.inventory_management_system.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userID = Firebase.auth.currentUser!!.uid


        //get instance of firestore db
        val db = Firebase.firestore
        val infoRef = db.collection("user").document(userID).collection("profile").document("info")
        infoRef.get()
            .addOnSuccessListener {document ->
                if(document != null){
                    //get the data as cast to hashmap
                    val data = document.data as HashMap<*, *>
                    //get the username field and set text for greet user as the same value inside firestore
                    val username = data["username"] as String
                    tv_greet_user.text = "Hello, $username"
                }
            }







    }
}