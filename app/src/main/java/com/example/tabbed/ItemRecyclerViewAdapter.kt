package com.example.tabbed

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tabbed.ForeCast.DailyForecast


import com.example.tabbed.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
val arr :List<Int> = listOf<Int>(1,2,3,4,5)

class ItemRecyclerViewAdapter(
    private val values: List<DailyForecast>
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.hotTemp.setText(item.Temperature.Maximum.Value.toString())
        holder.coldTemp.setText(item.Temperature.Minimum.Value.toString())
        holder.dateTemp.setText(item.Date.substring(0,10))
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val coldImageView: ImageView = binding.itemImageViewCold
        val hotImageView :ImageView = binding.itemImageViewHot

        val hotTemp: TextView = binding.hotTemp
        val coldTemp: TextView = binding.coldTemp
        val dateTemp :TextView = binding.date

        override fun toString(): String {
            return super.toString() + " '" + coldTemp.text + "'"
        }
    }

}