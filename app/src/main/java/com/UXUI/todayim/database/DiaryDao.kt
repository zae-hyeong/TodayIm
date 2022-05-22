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

    @Query("SELECT * FROM DiaryEmotionCategory WHERE diaryIdx = :diaryIdx ORDER BY adjectiveCategoryIdx ASC")
    fun getDiaryCategories(diaryIdx: Int): List<DiaryEmotionCategory>

    @Query("SELECT * FROM DiaryEmotionDetail WHERE diaryIdx = :diaryIdx ORDER BY adjectiveCategoryIdx ASC")
    fun getDiaryAdjectives(diaryIdx: Int): List<DiaryEmotionDetail>

    @Query("SELECT count(adjective) " +
            "FROM DiaryEmotionCategory AS category " +
            "    LEFT JOIN DiaryEmotionDetail AS adjective ON category.diaryIdx = adjective.diaryIdx " +
            "WHERE category.diaryIdx = :diaryIdx " +
            "GROUP BY category.adjectiveCategoryIdx " +
            "ORDER BY category.adjectiveCategoryIdx ASC")
    fun getCategoryAdjectiveNum(diaryIdx: Int): List<Int>
}