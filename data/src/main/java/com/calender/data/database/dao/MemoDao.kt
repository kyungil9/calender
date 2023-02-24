package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.calender.data.model.local.MemoLocal
import com.calender.domain.model.Memo
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Query("select * from memo")
    fun getAllMemoInfo(): Flow<List<MemoLocal>>

    @Insert
    fun insertMemo(memo : MemoLocal)

    @Update
    fun updateMemo(memo : MemoLocal)
    @Delete
    fun delectMemo(memo: MemoLocal)
}