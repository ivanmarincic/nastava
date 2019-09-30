package com.ivanmarincic.nastava.ui.classes

import androidx.lifecycle.ViewModel
import com.ivanmarincic.nastava.di.ViewModelKey
import com.ivanmarincic.nastava.di.scopes.ChildFragmentScoped
import com.ivanmarincic.nastava.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ClassesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeExploreFragment(): ClassesFragment

    @ChildFragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeFacultyListDialog(): FacultyListDialog

    @Binds
    @IntoMap
    @ViewModelKey(ClassesViewModel::class)
    abstract fun bindClassesViewModel(viewModel: ClassesViewModel): ViewModel
}