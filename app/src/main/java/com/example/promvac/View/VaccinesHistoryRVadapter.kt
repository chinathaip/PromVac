package com.example.promvac.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promvac.R
import com.example.promvac.databinding.ViewholderVaccinehistoryRvBinding

class VaccinesHistoryRVadapter(val vaccinesList:ArrayList<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class vaccinesListViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val vacName = view.findViewById<TextView>(R.id.vaccineName)
        val numberOfDose = view.findViewById<TextView>(R.id.numberDose)
        val receivedDate = view.findViewById<TextView>(R.id.receivedDate)
        val atHospital = view.findViewById<TextView>(R.id.vaccinationHospital)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val viewholder = LayoutInflater.from(context).inflate(R.layout.viewholder_vaccinehistory_rv,parent,false)
        return vaccinesListViewHolder(viewholder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }




}
