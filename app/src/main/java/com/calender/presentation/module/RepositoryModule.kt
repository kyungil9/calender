package com.calender.presentation.module

import com.calender.data.repository.*
import com.calender.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCalenderRepository(impl : CalenderRepositoryImpl) : CalenderRepository

    @Binds
    abstract fun bindToDoRepository(impl : ToDoRepositoryImpl) : ToDoRepository

    @Binds
    abstract fun bindMemoRepository(impl : MemoRepositoryImpl) : MemoRepositoy

    @Binds
    abstract fun bindRecordRepository(impl : RecordRepositoryImpl) : RecordRepository

    @Binds
    abstract fun bindTagRepository(impl : TagRepositoryImpl) : TagRepository
}