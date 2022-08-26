package com.tp.relevemeteo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import kotlinx.android.synthetic.main.meteo_item.view.*
import java.util.*

class MeteoAdapter(
    private var meteoList: List<Meteo>, private val listener: MeteoItemClickListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MeteoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.meteo_item, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int = meteoList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        meteoList[position].let { meteo -> holder.bind(meteo) }
    }

    fun updateList(it: List<Meteo>) {
        this.meteoList = it
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val listener: MeteoItemClickListener) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(
            meteo: Meteo
        ) {
            itemView.meteo_temperature.text = "${meteo.temperature}°C"
            itemView.meteo_date.text = "Date : ${dateToDisplayableString(meteo.date)}"
            itemView.meteo_ensoleillement.setImageResource(getDrawableForEnsoleillement(meteo.weatherType))

            //listener call
            itemView.setOnClickListener { listener.onMeteoClick(meteo) }
            itemView.setOnLongClickListener {
                listener.onMeteoLongClick(meteo)
                return@setOnLongClickListener true
            }
        }
    }
}

/**
 * used to receive event when a meteo item is (long)clicked
 */
interface MeteoItemClickListener {
    fun onMeteoClick(meteo: Meteo)
    fun onMeteoLongClick(meteo: Meteo)
}


// ----------------------- UTILITIES ----------------------- //

/**
 * this method is a file function this mean that it can be accessed statically, this is is private it is only available in this file
 */
@DrawableRes
private fun getDrawableForEnsoleillement(ensoleillement: WeatherType): Int {
    return when (ensoleillement) {
        WeatherType.SOLEIL -> R.drawable.weather_sunny
        WeatherType.ORAGE -> R.drawable.weather_lightning
        WeatherType.PLUIT -> R.drawable.weather_rainy
        WeatherType.NUAGEUX -> R.drawable.weather_cloudy
        WeatherType.BRUME -> R.drawable.weather_fog
    }
}

/**
 * use [DEFAULT_DATE_FORMAT] to transform a [Date] to a string
 */
fun dateToDisplayableString(date: Date): String {
    return android.text.format.DateFormat.format(DEFAULT_DATE_FORMAT, date).toString()
}


