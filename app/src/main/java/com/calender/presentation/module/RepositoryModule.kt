package com.calender.presentation.module

import com.calender.data.repository.CalenderRepositoryImpl
import com.calender.data.repository.MemoRepositoryImpl
import com.calender.data.repository.RecordRepositoryImpl
import com.calender.data.repository.ToDoRepositoryImpl
import com.calender.domain.repository.CalenderRepository
import com.calender.domain.repository.MemoRepositoy
import com.calender.domain.repository.RecordRepository
import com.calender.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCalenderRepository(impl : CalenderRepositoryImpl) : CalenderRepository

    @Binds
    abstract fun bindToDoRepository(impl : ToDoRepositoryImpl) : ToDoRepository

    @Binds
    abstract fun bindMemoRepository(impl : MemoRepositoryImpl) : MemoRepositoy

    @Binds
    abstract fun bindRecordRepository(impl : RecordRepositoryImpl) : RecordRepository
}