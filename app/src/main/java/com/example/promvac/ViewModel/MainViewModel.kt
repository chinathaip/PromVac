package com.example.promvac.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.time.LocalDate

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _vaccinesList = MutableLiveData<String>()

    private val db = FirebaseFirestore.getInstance()
    val vaccinesList = _vaccinesList;

    @RequiresApi(Build.VERSION_CODES.O)
    private val astrazeneca = Vaccines.AstraZeneca(LocalDate.of(2022,6,25))
    private val pfizer = Vaccines.Pfizer(LocalDate.of(2022,7,18))



    fun updateText(button:Int){
        when(button){
            1->{
                getFromDB("FaEZVaRTQetrYNxEyEvQ"){vaccinesList.postValue(it)}
            }
            2->{
                getFromDB("a6bqdA0RkTgxgoo0Y5UH"){vaccinesList.postValue(it)}
            }
        }
    }

    fun getFromDB(fieldName:String,callBack:(String?)->Unit) =CoroutineScope(IO).launch{
        var vacName:String?=null
        val query = db.collection("vaccines").document(fieldName)

        query.get().addOnSuccessListener {
            vacName = it.getString("VaccineName")
            Log.i("LOL", vacName.toString())
            callBack(vacName)
        }
        query.get().addOnFailureListener{
            Log.i("LOL",it.toString())

        }
    }


}