package com.ivanmarincic.nastava.ui.classes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ivanmarincic.nastava.dataaccess.model.NastavniPlan
import com.ivanmarincic.nastava.dataaccess.model.NastavniPlanHeader
import com.ivanmarincic.nastava.dataaccess.model.PlanWithSemestar
import com.ivanmarincic.nastava.databinding.ItemClassBinding
import com.ivanmarincic.nastava.databinding.ItemClassHeaderBinding

class ClassesListAdapter(
    private val eventListener: ClassEventListener,
    private val header: String
) :
    ListAdapter<PlanWithSemestar, RecyclerView.ViewHolder>(ClassesDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val binding =
                ItemClassHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ClassesHeaderViewHolder(binding)
        } else {
            val binding =
                ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ClassesViewHolder(binding, eventListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ClassesViewHolder) {
            holder.bind(getItem(position) as NastavniPlan)
        } else if (holder is ClassesHeaderViewHolder) {
            holder.bind(getItem(position) as NastavniPlanHeader, header)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is NastavniPlanHeader) {
            1
        } else {
            2
        }
    }

    override fun submitList(list: List<PlanWithSemestar>?) {
        if (list != null) {
            val newList = mutableListOf<PlanWithSemestar>()
            var lastSemestar = -1
            list
                .sortedBy { it.semestar }
                .forEach {
                    val semestar = it.semestar
                    if (semestar != lastSemestar) {
                        newList.add(NastavniPlanHeader(semestar))
                        lastSemestar = semestar
                    } else {
                        newList.add(it)
                    }
                }
            super.submitList(newList)
        } else {
            super.submitList(list)
        }
    }
}

class ClassesViewHolder(
    private val binding: ItemClassBinding,
    private val eventListener: ClassEventListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NastavniPlan) {
        binding.viewModel = item
        binding.eventListener = eventListener
    }
}

class ClassesHeaderViewHolder(
    private val binding: ItemClassHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: NastavniPlanHeader, header: String) {
        binding.header = "$header ${item.semestar}"
    }
}

object ClassesDiff : DiffUtil.ItemCallback<PlanWithSemestar>() {
    override fun areItemsTheSame(oldItem: PlanWithSemestar, newItem: PlanWithSemestar): Boolean {
        if (oldItem::class == newItem::class) {
            return if (oldItem is NastavniPlan && newItem is NastavniPlan) {
                oldItem.id == newItem.id
            } else {
                oldItem.semestar == newItem.semestar
            }
        }
        return false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PlanWithSemestar, newItem: PlanWithSemestar): Boolean {
        if (oldItem::class == newItem::class) {
            return if (oldItem is NastavniPlan && newItem is NastavniPlan) {
                oldItem == newItem
            } else {
                oldItem.semestar == newItem.semestar
            }
        }
        return false
    }
}