package com.tp.relevemeteo

import java.util.*
import kotlin.collections.ArrayList

object MeteoCache{
    private val meteoList = ArrayList<Meteo>().apply {
        add(Meteo(31, Date(), WeatherType.SOLEIL))
        add(Meteo(21, Date(1546764811000), WeatherType.PLUIT))
        add(Meteo(3, Date(1544086411000), WeatherType.SOLEIL))
        add(Meteo(14, Date(1583484811000), WeatherType.NUAGEUX))
        add(Meteo(13, Date(1559811211000), WeatherType.ORAGE))
        add(Meteo(19, Date(1568451211000), WeatherType.PLUIT))
        add(Meteo(0, Date(1558515211000), WeatherType.NUAGEUX))
    }

    fun removeMeteo(meteo : Meteo)
    {
        meteoList.remove(meteo)
    }

    fun getList() = meteoList.toMutableList()

}