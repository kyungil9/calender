package com.calender.presentation.module

import com.calender.data.repository.local.impl.*
import com.calender.data.repository.local.interfaces.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindScheduleLocal(impl : ScheduleLocalDataSourceImpl) : ScheduleLocalDataSource

    @Binds
    abstract fun bindToDoLocal(impl : ToDoLocalDataSourceImpl) : ToDoLocalDataSource

    @Binds
    abstract fun bindMemoLocal(impl : MemoLocalDataSourceImpl) : MemoLocalDataSource

    @Binds
    abstract fun bindRecordLocal(impl : RecordLocalDataSourceImpl) : RecordLocalDataSource

    @Binds
    abstract fun bindTagLocal(impl : TagLocalDataSourceImpl) : TagLocalDataSource

}