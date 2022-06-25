package com.example.promvac.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.promvac.Model.Vaccines
import java.time.LocalDate

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _vaccinesList = MutableLiveData<Vaccines>()

    val vaccinesList = _vaccinesList;

    @RequiresApi(Build.VERSION_CODES.O)
    private val astrazeneca = Vaccines.AstraZeneca(LocalDate.of(2022,6,25))
    private val pfizer = Vaccines.Pfizer(LocalDate.of(2022,7,18))

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateText(button:Int){
        when(button){
            1->vaccinesList.postValue(astrazeneca)
            2->vaccinesList.postValue(pfizer)
        }
    }

}