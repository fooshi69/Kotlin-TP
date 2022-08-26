package com.tp.relevemeteo

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(), MeteoItemClickListener {

    companion object {
        val TAG = MainActivity::class.java.toString()
    }

    /**
     * Affected at the moment we need it (this prevent trying to init it on class creation when it is not possible for [ViewModelProviders])
     */
    val viewModel: ListMeteoViewModel by lazy { ViewModelProviders.of(this).get(ListMeteoViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayMeteoList()
    }

    /**
     * display the meteo list into [meteo_recycler_view]
     */
    private fun displayMeteoList() {
        meteo_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = MeteoAdapter(ArrayList(), this@MainActivity)

        viewModel.observableMeteoList.observe(this@MainActivity, Observer { meteoList ->
            adapter.updateList(meteoList)
        })

        meteo_recycler_view.adapter = adapter

    }

    override fun onMeteoClick(meteo: Meteo) {
        Log.i(TAG, meteo.toString())
    }

    override fun onMeteoLongClick(meteo: Meteo) {
        displayConfirmDeletionDialog(meteo, viewModel::removeMeteo)
    }

    /**
     * display a dialog asking the user to confirm the deletion
     *
     *
     * if he choose to confirm [successCallback] lambda will be executed
     */
    fun displayConfirmDeletionDialog(meteo: Meteo, successCallback: (meteo: Meteo) -> Unit) {
        AlertDialog.Builder(this)
            .setMessage("Confirmer la suppression de la meteo")
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                //we execute the success lambda
                successCallback(meteo)
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}
