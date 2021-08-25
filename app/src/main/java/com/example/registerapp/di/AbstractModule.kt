package com.example.registerapp.di

import com.example.registerapp.fragments.auth.AuthFragmentRepository
import com.example.registerapp.fragments.auth.AuthFragmentRepositoryImpl
import com.example.registerapp.fragments.main.MainFragmentRepository
import com.example.registerapp.fragments.main.MainFragmentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractModule {

    @Binds
    abstract fun providesMainFragmentRepository(impl: MainFragmentRepositoryImpl): MainFragmentRepository

    @Binds
    abstract fun providesAuthFragmentRepository(impl: AuthFragmentRepositoryImpl): AuthFragmentRepository
}