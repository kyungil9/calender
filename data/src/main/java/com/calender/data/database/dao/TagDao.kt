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

    @Query("select * from tag")
    fun getAllTag(): Flow<List<TagLocal>>

    @Query("select * from tag limit 1")
    fun getOneTag() : TagLocal

    @Insert
    fun insertTag(tag : TagLocal)

    @Query("Delete from tag where tag.tag = :tag")
    fun deleteTag(tag: String)
}