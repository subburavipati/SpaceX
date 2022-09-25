package com.android.spacex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.spacex.databinding.RowSpacexBinding
import com.android.spacex.model.SpaceDetails

class SpacexAdapter(val click: (SpaceDetails) -> Unit) :
    RecyclerView.Adapter<SpacexAdapter.ViewHolder>() {

    var items: List<SpaceDetails> = emptyList()

    fun updateItems(items: List<SpaceDetails>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowSpacexBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpaceDetails) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RowSpacexBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.parent.setOnClickListener {
            click(items[position])
        }
    }

    override fun getItemCount() = items.size
}