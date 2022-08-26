package com.tp.relevemeteo


/**
 * concatenante "°C" at the end of an int
 */
//TODO ceci est un exemple d'extension sur un Int pour le transformer en String
fun Int.asTemperatureString() = "$this°C"


//TODO faire une methode asDisplayableString() sur Date qui utilise DEFAULT_DATE_FORMAT (du fichier Meteo)


//TODO faire une extention getDrawableForEnsoleillement() sur WeatherType pour avoir les retours suivant:
//WeatherType.SOLEIL -> R.drawable.weather_sunny
//WeatherType.ORAGE -> R.drawable.weather_lightning
//WeatherType.PLUIT -> R.drawable.weather_rainy
//WeatherType.NUAGEUX -> R.drawable.weather_cloudy
//WeatherType.BRUME -> R.drawable.weather_fog

//TODO faire une extention getTintForEnsoleillement() sur WeatherType pour avoir les retours suivant:
//WeatherType.SOLEIL -> R.color.yellow_sunny
//WeatherType.ORAGE -> R.color.blue_orage
//WeatherType.PLUIT -> R.color.blue_rainy
//WeatherType.NUAGEUX -> R.color.white_cloud
//WeatherType.BRUME -> R.color.grey_fog

