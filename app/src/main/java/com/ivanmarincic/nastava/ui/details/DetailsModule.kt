package com.ivanmarincic.nastava.ui.details

import androidx.lifecycle.ViewModel
import com.ivanmarincic.nastava.di.ViewModelKey
import com.ivanmarincic.nastava.di.scopes.ChildFragmentScoped
import com.ivanmarincic.nastava.di.scopes.FragmentScoped
import com.ivanmarincic.nastava.ui.classes.ClassesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class DetailsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}