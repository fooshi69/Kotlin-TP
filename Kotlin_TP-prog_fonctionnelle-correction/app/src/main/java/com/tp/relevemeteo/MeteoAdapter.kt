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
            //.run() marche aussi car le type de retour n'importe peu,
            // par contre on fait des operation SUR itemView du coup on prefere que l'itemView corresponde à "this" dans le bloc (et pas "it")
            itemView.apply {
                meteo_temperature.text = meteo.temperature.asTemperatureString()
                meteo_date.text = "Date : ${meteo.date.asDisplayableString()}"
                meteo_ensoleillement.setImageResource(meteo.weatherType.getDrawableForEnsoleillement())
                meteo_ensoleillement.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        meteo.weatherType.getTintForEnsoleillement()
                    )
                )
                //listener call
                setOnClickListener { listener.onMeteoClick(meteo) }
                setOnLongClickListener {
                    listener.onMeteoLongClick(meteo)
                    return@setOnLongClickListener true
                }
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



