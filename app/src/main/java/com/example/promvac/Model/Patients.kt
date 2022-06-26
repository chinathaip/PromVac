package com.example.promvac.Model

import java.time.LocalDate
import java.util.*

data class Patients(
    var patientName: String?, var firstDoseDate: Vaccines?, var secondDoseDate:Vaccines?,)
