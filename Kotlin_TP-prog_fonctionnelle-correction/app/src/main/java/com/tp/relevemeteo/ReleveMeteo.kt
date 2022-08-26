package com.tp.relevemeteo

import java.util.*

/**
 * Data class to store a meteo with a releve date
 */
data class ReleveMeteo(
    var dateDeReleve: Date = Date(),
    //since ReleveMeteo is now a data class you have to store your constructor attributs as val/var for the class
    //also we don't want to duplicate them so we override the parent ones
    override var temperature: Int,
    override var date: Date,
    override var weatherType: WeatherType
) : Meteo(
    temperature,
    date,
    weatherType
)