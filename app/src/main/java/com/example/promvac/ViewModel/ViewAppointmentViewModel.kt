package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewAppointmentViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    // TODO: Look at activity&fragment lifecycle --> what can we do with the life cycle?

    private val _patientID = MutableLiveData<Int>()
    val patientID = _patientID;


    fun updateRecyclerView(){


    }



}