package com.example.promvac.Model

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDatabase {

    companion object{
        private var dbInstance:FirebaseFirestore?=null
        fun getInstance()=
            dbInstance?: FirebaseFirestore.getInstance().apply{
                dbInstance=this
            }
    }
}