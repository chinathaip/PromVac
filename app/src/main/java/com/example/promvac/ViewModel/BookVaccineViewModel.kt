package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.FirestoreDatabase
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.coroutineContext

class BookVaccineViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val db= FirestoreDatabase.getInstance()



    fun createNewAppointment(patientName:String,firstDose:Vaccines,secondDose:Vaccines)=
        CoroutineScope(IO).launch {

            val newPatient = Patients(patientName,firstDose,secondDose)
            var nextPatientID:Int? = 0
            getCurrentPatientID{currentID ->
                nextPatientID=currentID?.plus(1) //+1 so we write new data into the next document without overwriting
                Log.i("LOL","After: $nextPatientID")
                db.collection("patient").document(nextPatientID.toString()).set(newPatient)
                .addOnSuccessListener { Log.i("LOL","SUCCESS") }
                .addOnFailureListener{Log.i("LOL","Failed $it")}

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


    //create new variable and assign the patientID to it in the lambda
    fun getCurrentPatientID(callBack:(Int?)->Unit)= CoroutineScope(IO).launch {

        db.collection("patient").get().addOnSuccessListener {
            Log.i("LOL",it.size().toString())
            callBack(it.size())
        }
    }
}