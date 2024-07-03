package com.example.tabbed

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycling
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.tabbed.ForeCast.DailyForecast
import com.example.tabbed.ForeCast.Day
import com.example.tabbed.ForeCast.ForeCast
import com.example.tabbed.ForeCast.Headline
import com.example.tabbed.ForeCast.Maximum
import com.example.tabbed.ForeCast.Minimum
import com.example.tabbed.ForeCast.Night
import com.example.tabbed.ForeCast.Temperature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response


/**
 * A fragment representing a list of Items.
 */
class WeekForecastFragment : Fragment() {

    private var columnCount = 1
    lateinit var dailyForecastList :List<DailyForecast>
    lateinit var  headline: Headline
    lateinit var  response: Response<ForeCast>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        val recView = view.findViewById<RecyclerView>( R.id.list)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        if ( recView is RecyclerView) {
            with(recView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount) }

                    progressBar.setProgress(10,true)
                    progressBar.isVisible = true

                    CoroutineScope(Dispatchers.IO).launch {
                        response = try {
                            progressBar.setProgress(30,true)
                            api.retrofit.getFiveDayTemperature()

                        } catch (e: Exception) {
                            Log.v("response10", e.message.toString())
                            return@launch
                        }


                        if (response.isSuccessful) {
                            progressBar.setProgress(50,true)
                            launch(Dispatchers.Main) {
                                Log.v("Response10", "sucessful")
                                dailyForecastList = response.body()!!.DailyForecasts
                                adapter = ItemRecyclerViewAdapter(dailyForecastList) // Fix this in the adapter
                                addItemDecoration(DividerItemDecoration(this@with.context, DividerItemDecoration.VERTICAL))

                                Log.v("Response10", "Done")
                                progressBar.setProgress(100,true)
                                progressBar.isVisible=false
                            }

                        } else {
                            Log.v("Response10", "Error somewhere")
                        }
                    }

            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            WeekForecastFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}