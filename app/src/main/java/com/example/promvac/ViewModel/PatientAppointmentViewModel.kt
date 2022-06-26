package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientAppointmentViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val db = FirebaseFirestore.getInstance()

    fun getFromDB(tableName:String, patientID:Int?, callBack:(Patients)->Unit) =
        CoroutineScope(Dispatchers.IO).launch{
            val patientsDB = db.collection(tableName)
            val patientQuery=patientsDB.document(patientID.toString())
            val patient=Patients(null,null,null)
            patientQuery.get().addOnSuccessListener {
                query->
                query.getString("patientName").let{name->patient.patientName=name}

                db.collection("/patients/$patientID/firstDose").document("1").let{
                    it.addSnapshotListener{queryResult, _ ->
                        val vaccineName = queryResult?.getString("VaccineName")
                        val receivedDate = queryResult?.getDate("ReceivedDate")
                        val hospital = queryResult?.getString("Hospital")
                        val patientFirstDose = Vaccines(vaccineName,receivedDate,hospital)
                        patient.firstDoseDate=patientFirstDose
                    }
                }

                db.collection("/patients/$patientID/SecondDose").document("2").let {
                    it.addSnapshotListener{queryResult, _ ->
                        val vaccineName = queryResult?.getString("VaccineName")
                        val receivedDate = queryResult?.getDate("ReceivedDate")
                        val hospital = queryResult?.getString("Hospital")
                        val patientSecondtDose = Vaccines(vaccineName,receivedDate,hospital)
                        patient.secondDoseDate=patientSecondtDose
                        callBack(patient)

                    }

                }


            }
            patientQuery.get().addOnFailureListener{
                Log.i("LOL",it.toString())

            }
        }

}