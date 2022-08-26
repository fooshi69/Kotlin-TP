package com.tp.relevemeteo

import java.util.*

const val DEFAULT_DATE_FORMAT = "dd/MM/yyyy"

class Meteo(
    var temperature: Int = 10,
    var date: Date = Date(),
    var weatherType: WeatherType = WeatherType.NUAGEUX
)