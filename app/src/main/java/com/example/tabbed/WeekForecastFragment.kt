package com.example.tabbed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.tabbed.ForeCast.DailyForecast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A fragment representing a list of Items.
 */
class WeekForecastFragment : Fragment() {

    private var columnCount = 1
    lateinit var dailyForecast :List<DailyForecast>

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
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                try {
                CoroutineScope(Dispatchers.IO) .launch{
                    val response = api.retrofit.getFiveDayTemperature()

                    if (response.isSuccessful)
                    {
                        launch(Dispatchers.Main) {
                            Log.v("response10", "Success")
                            dailyForecast = response.body()!!.DailyForecasts                         }
                    }
                    else { Log.v("response10", response.code().toString().plus(response.message()))}

                }}
                catch (e: Exception){
                    Log.v("response10", e.message.toString())
                }
                adapter = ItemRecyclerViewAdapter(listOf(1,2,3,4,5))
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
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