package com.example.promvac.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.promvac.R
import com.example.promvac.ViewModel.ShowVaccineViewModel

class showVaccineFragment : Fragment() {

    companion object {
        fun newInstance() = showVaccineFragment()
    }

    private lateinit var viewModel: ShowVaccineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_vaccine, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowVaccineViewModel::class.java)
        // TODO: Use the ViewModel
    }

}