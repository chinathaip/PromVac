package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ShowVaccineViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val db = FirebaseFirestore.getInstance()

    private val _vaccineOfPatient = MutableLiveData<ArrayList<Vaccines?>>()

    private val _patientName = MutableLiveData<String>()

    val patientName = _patientName
    val vaccineOfPatient = _vaccineOfPatient

    var list = arrayListOf<Vaccines?>()
    //Vaccines("Johnson",Date(12),"Yanhee")

    fun updateRecyclerView(patientID:Int?){
        getFromDB("patients",patientID){ patient ->
            Log.i("LOL",patient.patientName.toString())
            list = arrayListOf(patient.firstDoseDate,patient.secondDoseDate)
            vaccineOfPatient.postValue(list)
            patientName.postValue(patient.patientName)

        }
    }


    fun getFromDB(tableName:String, patientID:Int?, callBack:(Patients)->Unit) =
        CoroutineScope(Dispatchers.IO).launch{
            val patientsDB = db.collection(tableName)
            val patientQuery=patientsDB.document(patientID.toString())
            val patient= Patients(null,null,null)
            patientQuery.get().addOnSuccessListener { query->
                query.getString("patientName").let{name->patient.patientName=name}

                //get first dose data
                db.collection("/patients/$patientID/firstDose").document("1").let{
                    it.addSnapshotListener{queryResult, _ ->
                        val vaccineName = queryResult?.getString("VaccineName")
                        val receivedDate = queryResult?.getDate("ReceivedDate")
                        val hospital = queryResult?.getString("Hospital")
                        val patientFirstDose = Vaccines(vaccineName,receivedDate,hospital)
                        patient.firstDoseDate=patientFirstDose
                    }

                    //follow by second dose data
                    db.collection("/patients/$patientID/SecondDose").document("2").let {
                        it.addSnapshotListener{queryResult, _ ->
                            val vaccineName = queryResult?.getString("VaccineName")
                            val receivedDate = queryResult?.getDate("ReceivedDate")
                            val hospital = queryResult?.getString("Hospital")
                            val patientSecondtDose = Vaccines(vaccineName,receivedDate,hospital)
                            patient.secondDoseDate=patientSecondtDose
                            callBack(patient) //run callback function here

                        }

                    }

                }
            }
            patientQuery.get().addOnFailureListener{
                    error->
                Log.i("LOL",error.toString())
            }
        }

}