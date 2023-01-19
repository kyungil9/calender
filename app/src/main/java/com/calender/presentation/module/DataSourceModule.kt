package com.calender.presentation.module

import com.calender.data.repository.local.impl.ScheduleLocalDataSourceImpl
import com.calender.data.repository.local.impl.ToDoLocalDataSourceImpl
import com.calender.data.repository.local.interfaces.ScheduleLocalDataSource
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindScheduleLocal(impl : ScheduleLocalDataSourceImpl) : ScheduleLocalDataSource

    @Binds
    abstract fun bindToDoLocal(impl : ToDoLocalDataSourceImpl) : ToDoLocalDataSource

}