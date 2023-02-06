package com.calender.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.calender.data.model.local.TagLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    @Query("select * from tag where mode = 0")
    fun getToDoAllTag(): Flow<List<TagLocal>>

    @Query("select * from tag where mode = 1")
    fun getRecordAllTag(): Flow<List<TagLocal>>

    @Query("select * from tag where mode = 0 limit 1")
    fun getToDoOneTag() : TagLocal

    @Insert
    fun insertTag(tag : TagLocal)

    @Query("Delete from tag where tag.tag = :tag and mode = :mode")
    fun deleteTag(tag: String,mode : Int)
}