package com.example.groovy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groovy.R
import com.example.groovy.databinding.ItemPlaylistBinding
import com.example.groovy.model.ModelPlaylistItem

class PlaylistRecyclerViewAdapter(
) : RecyclerView.Adapter<PlaylistRecyclerViewAdapter.ViewHolder>() {

    private var values: List<ModelPlaylistItem> = emptyList()

    fun setData(values: List<ModelPlaylistItem>) {
        this.values = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder.binding) {
            playlistName.text = item.name
            playlistCategory.text = item.category
            playlistImage.setImageResource(R.drawable.playlist)
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}