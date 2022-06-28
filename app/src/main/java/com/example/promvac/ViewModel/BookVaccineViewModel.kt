package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class BookVaccineViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val db= FirebaseFirestore.getInstance()

    val currentPatientID = 4

    fun createNewAppointment(patientName:String,firstDose:Vaccines,secondDose:Vaccines)=
        CoroutineScope(IO).launch {
            val newPatient = Patients(patientName,firstDose,secondDose)
            val patientAttribute = db.collection("patients").document(currentPatientID.toString())
            patientAttribute.set(newPatient).let {
                it.addOnSuccessListener {
                    Log.i("LOL","Success")
                }
                it.addOnFailureListener{
                    Log.i("LOL","Error $it")
                }
            }
        }
}