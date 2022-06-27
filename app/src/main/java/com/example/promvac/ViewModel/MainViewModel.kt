package com.example.promvac.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Vaccines
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.time.LocalDate

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _vaccinesList = MutableLiveData<String>()


    val vaccinesList = _vaccinesList;

//    @RequiresApi(Build.VERSION_CODES.O)
//    private val astrazeneca = Vaccines.AstraZeneca(LocalDate.of(2022,6,25))
//    private val pfizer = Vaccines.Pfizer(LocalDate.of(2022,7,18))



//    fun updateText(button:Int){
//        when(button){
//            1->{
//                getFromDB("patients","DThLSva2xHwXsNRZnELs"){vaccinesList.postValue(it)}
//            }
//            2->{
//                getFromDB("patients","kyvLdH9CG3fKfiQ3YEI0"){vaccinesList.postValue(it)}
//            }
//        }
//    }




}