package com.ivanmarincic.nastava.ui.classes

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivanmarincic.nastava.dataaccess.model.Fakultet
import com.ivanmarincic.nastava.databinding.ItemFacultyBinding


class FacultyListAdapter(
    private val eventListener: FacultyEventListener,
    var selectedItem: Fakultet?,
    private val selectedBackground: Drawable,
    private val selectableBackground: Drawable
) :
    ListAdapter<Fakultet, FacultyViewHolder>(FacultyDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FacultyViewHolder(binding, eventListener)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, item.id == selectedItem?.id, selectedBackground, selectableBackground)
    }
}

class FacultyViewHolder(
    private val binding: ItemFacultyBinding,
    private val eventListener: FacultyEventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Fakultet, selected: Boolean, selectedBackground: Drawable, selectableBackground: Drawable) {
        binding.viewModel = item
        binding.eventListener = eventListener
        if (selected) {
            binding.root.background = selectedBackground
        } else {
            binding.root.background = selectableBackground
        }
    }

}

object FacultyDiff : DiffUtil.ItemCallback<Fakultet>() {
    override fun areItemsTheSame(oldItem: Fakultet, newItem: Fakultet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Fakultet, newItem: Fakultet): Boolean {
        return oldItem == newItem
    }
}