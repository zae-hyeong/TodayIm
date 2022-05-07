package com.UXUI.todayim.database

import androidx.room.*

@Dao // Data Access Object
interface DiaryDao {
    @Insert
    fun insertDiaryData(diaryInfo: DiaryInfo)

    @Update
    fun updateDiaryInfo(diaryInfo: DiaryInfo)

    @Delete
    fun deleteDiaryInfo(diaryInfo: DiaryInfo)

    // ==== QUERY ===== //
    @Query("SELECT * FROM DiaryInfo ORDER BY diaryDate DESC")
    fun getAllDiaryData(): List<DiaryInfo>
}