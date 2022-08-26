package com.tp.relevemeteo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tp.relevemeteo.*

class ListMeteoViewModel : ViewModel() {

    /**
     * a sorted/filtered observable list
     */
    val mutableMeteoList = MutableLiveData<List<Meteo>>().apply { value = MeteoCache.getList() }


    fun refreshListMeteo() {
        mutableMeteoList.value = MeteoCache.getList()
    }

    fun removeMeteo(meteo: Meteo) {
        MeteoCache.removeMeteo(meteo)
        refreshListMeteo()
    }

}
