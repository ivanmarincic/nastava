package com.ivanmarincic.nastava.ui

import androidx.lifecycle.ViewModel
import com.ivanmarincic.nastava.di.ViewModelKey
import com.ivanmarincic.nastava.di.scopes.ChildFragmentScoped
import com.ivanmarincic.nastava.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindClassesViewModel(viewModel: MainViewModel): ViewModel
}