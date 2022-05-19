package com.UXUI.todayim.database

import androidx.room.*

@Dao // Data Access Object
interface DiaryDao {
    @Insert
    fun insertDiaryData(diary: Diary)

    @Insert
    fun insertDiaryEmotionCategory(diaryEmotionCategory: DiaryEmotionCategory)

    @Insert
    fun insertDiaryEmotionDetail(diaryEmotionDetail: DiaryEmotionDetail)

    @Update
    fun updateDiaryInfo(diary: Diary)

    @Delete
    fun deleteDiaryInfo(diary: Diary)

    // ==== QUERY ===== //
    @Query("SELECT diaryIdx FROM Diary WHERE diaryDate = :date")
    fun getDiaryIdx(date: String): Int

    @Query("SELECT * FROM Diary ORDER BY diaryDate DESC")
    fun getAllDiaryData(): List<Diary>

    @Query("SELECT * FROM Diary WHERE diaryDate = :date")
    fun getFindSpecificData(date: String): Diary

//    @Query("SELECT diaryIdx, diaryComment, diaryDate, diaryCategoryIdx, diaryEmotionIdx FROM Diary" +
//            "    LEFT JOIN DiaryEmotionCategory ON DiaryEmotionCategory.diaryIdx = Diary.diaryIdx" +
//            "    LEFT JOIN DiaryEmotionDetail ON DiaryEmotionDetail.diaryIdx = Diary.diaryIdx" +
//            "    WHERE diaryDate = :date")
//    fun getRecord(date: String): Diary
}