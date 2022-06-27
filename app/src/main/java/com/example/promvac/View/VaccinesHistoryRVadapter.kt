package com.example.promvac.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promvac.Model.Vaccines
import com.example.promvac.R

class VaccinesHistoryRVadapter(var vaccineList: ArrayList<Vaccines?>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int{
        when(position>=0){
            true-> return R.layout.viewholder_vaccinehistory_rv
            else -> return 9999
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = when(viewType){
        R.layout.viewholder_vaccinehistory_rv->{
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_vaccinehistory_rv,parent,false))
        }

        else -> {throw NullPointerException("Unidentified viewholder")}
    }



    override fun getItemCount(): Int {
        return vaccineList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder->{
                val vaccine = vaccineList[position]
                val doseNumber = position+1
                holder.vacName.text =holder.itemView.context.getString(R.string.vaccine_name,vaccine?.vacName)
                holder.numberOfDose.text=holder.itemView.context.getString(R.string.dose_number,doseNumber)
                holder.receivedDate.text=holder.itemView.context.getString(R.string.received_date,vaccine?.date?.date.toString(),vaccine?.date?.month.toString(),vaccine?.date?.year.toString())
                holder.atHospital.text=holder.itemView.context.getString(R.string.hospital,vaccine?.hospital)
            }
        }

    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var vacName = view.findViewById<TextView>(R.id.vaccineName)
        val numberOfDose = view.findViewById<TextView>(R.id.numberDose)
        val receivedDate = view.findViewById<TextView>(R.id.receivedDate)
        val atHospital = view.findViewById<TextView>(R.id.vaccinationHospital)
    }

}

