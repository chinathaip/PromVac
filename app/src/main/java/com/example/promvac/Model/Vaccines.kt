package com.example.promvac.Model

import java.time.LocalDate

sealed class Vaccines{
    data class AstraZeneca(val firstDoseDate: LocalDate): Vaccines()
    data class Johnson(val firstDoseDate: LocalDate): Vaccines()
    data class Pfizer (val firstDoseDate: LocalDate):Vaccines()
    data class Sinovac(val firstDoseDate: LocalDate):Vaccines()
}
