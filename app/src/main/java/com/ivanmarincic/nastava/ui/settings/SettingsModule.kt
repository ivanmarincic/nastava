package com.ivanmarincic.nastava.ui.settings

import androidx.lifecycle.ViewModel
import com.ivanmarincic.nastava.di.ViewModelKey
import com.ivanmarincic.nastava.di.scopes.ChildFragmentScoped
import com.ivanmarincic.nastava.di.scopes.FragmentScoped
import com.ivanmarincic.nastava.ui.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class SettingsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ChildFragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeChooseThemeDialog(): ChooseThemeDialog

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: SettingsViewModel): ViewModel
}