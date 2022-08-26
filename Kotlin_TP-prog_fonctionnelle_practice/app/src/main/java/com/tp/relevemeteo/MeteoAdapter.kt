package com.tp.relevemeteo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.meteo_item.view.*

class MeteoAdapter(
    var meteoList: List<Meteo>, val listener: MeteoItemClickListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MeteoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.meteo_item, parent, false)
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

    class ViewHolder(itemView: View, val listener: MeteoItemClickListener) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(
            meteo: Meteo
        ) {
            //display data
            itemView.meteo_temperature.text = meteo.temperature.asTemperatureString()
            // TODO need extention to work
            itemView.meteo_date.text = "Date : ${meteo.date.asDisplayableString()}"
            // TODO need extention to work
            itemView.meteo_ensoleillement.setImageResource(meteo.weatherType.getDrawableForEnsoleillement())
            itemView.meteo_ensoleillement.setColorFilter(
                ContextCompat.getColor(
                    itemView.context,
                    // TODO need extention to work
                    meteo.weatherType.getTintForEnsoleillement()
                )
            )
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



