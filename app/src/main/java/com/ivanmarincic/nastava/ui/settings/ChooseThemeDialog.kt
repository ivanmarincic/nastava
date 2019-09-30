package com.ivanmarincic.nastava.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.ivanmarincic.nastava.R
import com.ivanmarincic.nastava.custom.CustomDialogFragment
import com.ivanmarincic.nastava.databinding.DialogChooseThemeBinding
import com.ivanmarincic.nastava.util.SharedPreferenceStorage
import javax.inject.Inject


class ChooseThemeDialog(title: String) :
    CustomDialogFragment(title) {

    constructor() : this("")

    @Inject
    lateinit var sharedPreferenceStorage: SharedPreferenceStorage

    private lateinit var binding: DialogChooseThemeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogChooseThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val selectedValue = sharedPreferenceStorage.darkTheme
        binding.group.apply {
            setOnCheckedChangeListener { _, i ->
                val value = when (i) {
                    R.id.first -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    R.id.second -> AppCompatDelegate.MODE_NIGHT_YES
                    R.id.third -> AppCompatDelegate.MODE_NIGHT_NO
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
                sharedPreferenceStorage.darkTheme = value
                AppCompatDelegate.setDefaultNightMode(value)
            }
            check(
                when (selectedValue) {
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> R.id.first
                    AppCompatDelegate.MODE_NIGHT_YES -> R.id.second
                    AppCompatDelegate.MODE_NIGHT_NO -> R.id.third
                    else -> R.id.first
                }
            )
        }
    }
}