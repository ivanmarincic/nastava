package com.ivanmarincic.nastava.ui.classes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ivanmarincic.nastava.R
import com.ivanmarincic.nastava.dataaccess.model.NastavniPlan
import com.ivanmarincic.nastava.databinding.FragmentClassesBinding
import com.ivanmarincic.nastava.ui.MainViewModel
import com.ivanmarincic.nastava.ui.NavigationHost
import com.ivanmarincic.nastava.util.activityViewModelProvider
import com.ivanmarincic.nastava.util.observeSingle
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ClassesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentClassesBinding
    private lateinit var viewModel: ClassesViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ClassesListAdapter

    private var navigationHost: NavigationHost? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHost) {
            navigationHost = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activityViewModelProvider(viewModelFactory)
        mainViewModel = activityViewModelProvider(viewModelFactory)
        binding = FragmentClassesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (navigationHost != null) {
            val mainToolbar: Toolbar = binding.toolbar
            mainToolbar.apply {
                navigationHost?.registerToolbarWithNavigation(this, R.string.classes_fragment_title)
            }
        }
        binding.progress.hide()
        binding.toolbar.run {
            inflateMenu(R.menu.classes_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.classes_menu_faculty_list -> {
                        openFacultyListDialog()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.classes_menu_settings -> {
                        openSettings()
                        return@setOnMenuItemClickListener true
                    }
                }
                return@setOnMenuItemClickListener false
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.selectedStudij.observeSingle(this, Observer {
            binding.progress.show()
            viewModel.loadNastavniPlan(mainViewModel.selectedFaculty.value!!, it)
        })
        mainViewModel.studijList.observeSingle(activity!!, Observer {
            binding.progress.hide()
        })
        viewModel.nastavniPlanList.observeSingle(this, Observer { list ->
            adapter.submitList(list)
            binding.progress.hide()
        })
        viewModel.selectedNastavniPlan.observeSingle(this, Observer {
            openDetails(it)
        })
        val list = binding.classesList
        if (list.adapter == null) {
            adapter =
                ClassesListAdapter(viewModel, context!!.getString(R.string.classes_list_header))
            list.adapter = adapter
        }
        (list.adapter as ClassesListAdapter).submitList(
            viewModel.nastavniPlanList.value ?: emptyList()
        )
    }

    private fun openFacultyListDialog() {
        val selected = mainViewModel.selectedFaculty.value
        if (selected != null) {
            viewModel.selectFakultet(selected)
        }
        val facultyListDialog =
            FacultyListDialog(context!!.resources.getString(R.string.faculty_list_dialog_title))
        facultyListDialog.show(fragmentManager!!, "FacultyListFragment")
        facultyListDialog.setOnDismissListener {
            val value = viewModel.selectedFaculty.value
            if (value != null) {
                binding.progress.show()
                mainViewModel.setFakultet(value)
            }
        }
    }

    private fun openSettings() {
        findNavController().navigate(R.id.action_navigation_classes_to_navigation_settings)
    }

    private fun openDetails(nastavniPlan: NastavniPlan) {
        findNavController().navigate(R.id.action_navigation_classes_to_navigation_details)
    }
}