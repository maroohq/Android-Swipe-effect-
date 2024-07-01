package com.example.tabbed

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.tabbed.placeholder.PlaceholderContent.PlaceholderItem
import com.example.tabbed.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
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
        holder.hotTemp.setText("25")
        holder.coldTemp.setText("16")
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val coldImageView: ImageView = binding.itemImageViewCold
        val hotImageView :ImageView = binding.itemImageViewHot

        val hotTemp: TextView = binding.hotTemp
        val coldTemp: TextView = binding.coldTemp

        override fun toString(): String {
            return super.toString() + " '" + coldTemp.text + "'"
        }
    }

}