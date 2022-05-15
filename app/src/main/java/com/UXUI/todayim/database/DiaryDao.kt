package com.UXUI.todayim.database

import androidx.room.*

@Dao // Data Access Object
interface DiaryDao {
    @Insert
    fun insertDiaryData(diary: Diary)

    @Update
    fun updateDiaryInfo(diary: Diary)

    @Delete
    fun deleteDiaryInfo(diary: Diary)

    // ==== QUERY ===== //
    @Query("SELECT * FROM Diary ORDER BY diaryDate DESC")
    fun getAllDiaryData(): List<Diary>

    @Query("SELECT * FROM Diary WHERE diaryDate = :date")
    fun getFindSpecificData(date: String): Diary
}