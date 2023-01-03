package com.calender.main.data.database

import android.content.Context
import android.widget.Toast
import com.calender.main.data.entity.User
import com.google.firebase.firestore.FirebaseFirestore

class DB(Uid: String?) {
    val db = FirebaseFirestore.getInstance().collection("users").document("$Uid")

    fun createUser(context: Context, user: User){
        db.set(user)
            .addOnSuccessListener {
                Toast.makeText(context,  "db success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context,  "db fail", Toast.LENGTH_SHORT).show()
            }
    }


}
