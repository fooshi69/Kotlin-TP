package com.tp.relevemeteo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListMeteoViewModel : ViewModel() {

    /**
     * a sorted/filtered observable list
     */
    val observableMeteoList = MutableLiveData<List<Meteo>>().apply { value = MeteoCache.getList() }


    fun refreshListMeteo() {
        observableMeteoList.value = MeteoCache.getList()
    }

    fun removeMeteo(meteo : Meteo)
    {
        MeteoCache.removeMeteo(meteo)
        refreshListMeteo()
    }

}
