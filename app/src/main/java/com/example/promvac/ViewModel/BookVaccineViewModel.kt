package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class BookVaccineViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val db= FirebaseFirestore.getInstance()



    fun createNewAppointment(patientName:String,firstDose:Vaccines,secondDose:Vaccines)=
        CoroutineScope(IO).launch {
            val newPatient = Patients(patientName,firstDose,secondDose)
            var patientID = getCurrentPatientID{
                val patientAttribute = db.collection("patients").document(it.toString())

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

    fun createVaccine(vaccineName:String,firstDoseDate:Date,hospital:String):Vaccines{
        val vacName:String?
        when(vaccineName){
            "Astrazeneca"->vacName="Astrazeneca"
            "Pfizer"->vacName="Pfizer"
            "Sinovac"->vacName="Sinovac"
            "Johnson"->vacName="Johnson"
            else->vacName="Unidentified"
        }
        return Vaccines(vacName,firstDoseDate,hospital)
    }


    //TODO: one click causes 999999 writes to the database
    fun getCurrentPatientID(callBack:(Int?)->Unit)= CoroutineScope(IO).launch {

        db.collection("patients").get().addOnSuccessListener {
            Log.i("LOL",it.size().toString())
            callBack(it.size()+1)
        }
    }
}