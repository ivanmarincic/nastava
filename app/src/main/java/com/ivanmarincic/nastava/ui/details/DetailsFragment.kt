package com.ivanmarincic.nastava.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.ivanmarincic.nastava.R
import com.ivanmarincic.nastava.databinding.FragmentDetailsBinding
import com.ivanmarincic.nastava.ui.classes.ClassesViewModel
import com.ivanmarincic.nastava.util.activityViewModelProvider
import com.ivanmarincic.nastava.util.parentViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class DetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var classesViewModel: ClassesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = parentViewModelProvider(viewModelFactory)
        classesViewModel = activityViewModelProvider(viewModelFactory)
        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = classesViewModel.selectedNastavniPlan.value
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            title = context.getString(R.string.details_fragment_title)
        }
    }
}