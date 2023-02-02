package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.calender.data.model.local.TagLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    @Query("select * from tag")
    fun getAllTag(): Flow<List<TagLocal>>

    @Insert
    fun insertTag(tag : TagLocal)

    @Delete
    fun deleteTag(tag: TagLocal)
}