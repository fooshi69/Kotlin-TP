package com.tp.relevemeteo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    private val viewModel: ListMeteoViewModel by lazy { ViewModelProviders.of(this).get(ListMeteoViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayMeteoList()
    }

    /**
     * display the [displayedList] into [meteo_recycler_view]
     */
    private fun displayMeteoList() {
        meteo_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)

        //create adapter for recycler view
        val adapter =
            MeteoAdapter(ArrayList(),/*TODO Ajouter le listener MeteoItemClickListener qui permettra sur un clic long de supprimer la liste */)


        //this block automaticaly update our Recycler view (using LiveData)
        viewModel.mutableMeteoList.observe(this, Observer { meteoList ->
            adapter.updateList(meteoList)
        })

        //set recycler view adapter
        meteo_recycler_view.adapter = adapter
    }
}
