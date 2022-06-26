package com.example.promvac.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promvac.Model.Patients
import com.example.promvac.Model.Vaccines
import com.example.promvac.R
import com.example.promvac.databinding.ViewholderVaccinehistoryRvBinding

class VaccinesHistoryRVadapter(var vaccineList: ArrayList<Vaccines?>):
    RecyclerView.Adapter<VaccinesHistoryRVadapter.vaccinesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vaccinesListViewHolder {
        val context = parent.context
        val viewholder = LayoutInflater.from(context).inflate(R.layout.viewholder_vaccinehistory_rv,parent,false)
        return vaccinesListViewHolder(viewholder)
    }



    class vaccinesListViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var vacName = view.findViewById<TextView>(R.id.vaccineName)
        val numberOfDose = view.findViewById<TextView>(R.id.numberDose)
        val receivedDate = view.findViewById<TextView>(R.id.receivedDate)
        val atHospital = view.findViewById<TextView>(R.id.vaccinationHospital)
    }

    override fun onBindViewHolder(holder: vaccinesListViewHolder, position: Int) {
        val vaccine = vaccineList[position]
        val doseNumber = position+1
        holder.vacName.text =holder.itemView.context.getString(R.string.vaccine_name,vaccine?.vacName)
        holder.numberOfDose.text=holder.itemView.context.getString(R.string.dose_number,doseNumber)
        holder.receivedDate.text=holder.itemView.context.getString(R.string.received_date,vaccine?.date?.toString())
        holder.atHospital.text=holder.itemView.context.getString(R.string.hospital,vaccine?.hospital)


    }
    override fun getItemCount(): Int {
        return vaccineList.size
    }

}

