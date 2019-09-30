package com.ivanmarincic.nastava.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ivanmarincic.nastava.R
import com.ivanmarincic.nastava.databinding.FragmentSettingsBinding
import dagger.android.support.DaggerFragment


class SettingsFragment : DaggerFragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            title = context.getString(R.string.settings_fragment_title)
        }
        binding.chooseTheme.setOnClickListener {
            openThemeDialog()
        }
    }

    fun openThemeDialog() {
        val chooseThemeDialog =
            ChooseThemeDialog(context!!.resources.getString(R.string.choose_theme_dialog_title))
        chooseThemeDialog.show(fragmentManager!!, "FacultyListFragment")
    }
}