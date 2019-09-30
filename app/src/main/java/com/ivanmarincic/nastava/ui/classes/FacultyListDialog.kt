package com.ivanmarincic.nastava.ui.classes

import android.R
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivanmarincic.nastava.custom.CustomDialogFragment
import com.ivanmarincic.nastava.dataaccess.api.FakultetiService
import com.ivanmarincic.nastava.dataaccess.model.Fakultet
import com.ivanmarincic.nastava.databinding.DialogFacultyListBinding
import com.ivanmarincic.nastava.util.activityViewModelProvider
import javax.inject.Inject


class FacultyListDialog(title: String) :
    CustomDialogFragment(title) {

    constructor() : this("")

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fakultetiService: FakultetiService

    private lateinit var binding: DialogFacultyListBinding
    private lateinit var viewModel: ClassesViewModel
    private lateinit var adapter: FacultyListAdapter
    private var list: List<Fakultet> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activityViewModelProvider(viewModelFactory)
        binding = DialogFacultyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val ta = context!!.obtainStyledAttributes(attrs)
        val selectableItemBackground = ta.getDrawable(0)
        ta.recycle()
        val selectedItemBackground =
            ColorDrawable(
                ContextCompat.getColor(
                    context!!,
                    com.ivanmarincic.nastava.R.color.color_text_on_background
                )
            )
        selectedItemBackground.alpha = 50
        adapter = FacultyListAdapter(
            viewModel,
            viewModel.selectedFaculty.value,
            selectedItemBackground,
            selectableItemBackground!!
        )
        binding.progress.show()
        binding.facultyList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FacultyListDialog.adapter
        }
        binding.save.setOnClickListener {
            dismiss()
        }
        viewModel.facultyList.observe(this, Observer {
            list = it
            adapter.submitList(it)
            binding.progress.hide()
        })
        viewModel.selectedFaculty.observe(this, Observer {
            adapter.selectedItem = it
            adapter.notifyItemChanged(list.indexOf(it))
        })
    }
}