package com.calender.main.data.module

import com.calender.data.repository.CalenderRepositoryImpl
import com.calender.domain.repository.CalenderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCalenderRepository(impl : CalenderRepositoryImpl) : CalenderRepository
}