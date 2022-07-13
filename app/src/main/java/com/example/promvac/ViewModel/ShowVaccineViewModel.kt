package com.example.promvac.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.FirestoreDatabase
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.cast

class ShowVaccineViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    private val db = FirestoreDatabase.getInstance()

    private val _vaccineOfPatient = MutableLiveData<ArrayList<Vaccines?>>()

    private val _patientName = MutableLiveData<String>()

    val patientName = _patientName
    val vaccineOfPatient = _vaccineOfPatient

    var list = arrayListOf<Vaccines?>()
    //Vaccines("Johnson",Date(12),"Yanhee")

    fun updateRecyclerView(patientID:Int?){
        getFromDB("patient",patientID){ patient ->
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

                val firstDoseQuery = query.get("firstDoseDate") as Map<String,*>?
                with(firstDoseQuery){

                    val vaccineName = this?.get("vacName") as String?
                    val hospitalName = this?.get("hospital")as String?
                    val rawDate = this?.get("date") as com.google.firebase.Timestamp?
                    val appointedDate= rawDate?.toDate()
                    val firstDose = Vaccines(vaccineName,appointedDate,hospitalName)
                    patient.firstDoseDate=firstDose
                }

                val secondDoseQuery = query.get("secondDoseDate") as Map<String,*>?
                with(secondDoseQuery){
                    val vaccineName = this?.get("vacName") as String?
                    val hospitalName = this?.get("hospital")as String?
                    val rawDate = this?.get("date") as com.google.firebase.Timestamp?
                    val appointedDate= rawDate?.toDate()
                    val secondDose = Vaccines(vaccineName,appointedDate,hospitalName)
                    patient.secondDoseDate=secondDose
                    callBack(patient)
                }
            }
            patientQuery.get().addOnFailureListener{
                    error->
                Log.i("LOL",error.toString())
            }
        }

}