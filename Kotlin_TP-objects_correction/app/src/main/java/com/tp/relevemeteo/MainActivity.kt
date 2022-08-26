package com.tp.relevemeteo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.toString()
    }

    val viewModel: ListMeteoViewModel by lazy {
        ViewModelProviders.of(this).get(ListMeteoViewModel::class.java)
    }

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
        val adapter = MeteoAdapter(ArrayList(), object : MeteoItemClickListener {
            override fun onMeteoClick(meteo: Meteo) {
                //"MainActivity." here force us to use a companion object instead of another solution
                Log.i(TAG, meteo.toString())
            }

            override fun onMeteoLongClick(meteo: Meteo) {
                viewModel.removeMeteo(meteo)
            }

        })


        //this block automaticaly update our Recycler view (using LiveData)
        viewModel.mutableMeteoList.observe(this, Observer { meteoList ->
            adapter.updateList(meteoList)
        })

        //set recycler view adapter
        meteo_recycler_view.adapter = adapter
    }
}
