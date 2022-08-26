package com.tp.relevemeteo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import java.util.*

/**
 * use [DEFAULT_DATE_FORMAT] to transform a [Date] to a string
 */
fun Date.asDisplayableString() = android.text.format.DateFormat.format(DEFAULT_DATE_FORMAT, this).toString()

/**
 * concatenante "°C" at the end of an int
 */
fun Int.asTemperatureString() = "$this°C"

@DrawableRes
fun WeatherType.getDrawableForEnsoleillement(): Int {
    return when (this) {
        WeatherType.SOLEIL -> R.drawable.weather_sunny
        WeatherType.ORAGE -> R.drawable.weather_lightning
        WeatherType.PLUIT -> R.drawable.weather_rainy
        WeatherType.NUAGEUX -> R.drawable.weather_cloudy
        WeatherType.BRUME -> R.drawable.weather_fog
    }
}

@ColorRes
fun WeatherType.getTintForEnsoleillement(): Int {
    return when (this) {
        WeatherType.SOLEIL -> R.color.yellow_sunny
        WeatherType.ORAGE -> R.color.blue_orage
        WeatherType.PLUIT -> R.color.blue_rainy
        WeatherType.NUAGEUX -> R.color.white_cloud
        WeatherType.BRUME -> R.color.grey_fog
    }
}
