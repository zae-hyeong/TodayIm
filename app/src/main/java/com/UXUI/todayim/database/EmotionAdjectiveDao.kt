package com.UXUI.todayim.database

import androidx.room.*

@Dao // Data Access Object
interface EmotionAdjectiveDao {
    @Insert
    fun insertCategory(category: EmotionAdjectiveCategory)

    @Insert
    fun insertAdjective(adjective: EmotionAdjective)

//    // ==== QUERY ===== //

    @Query("SELECT * FROM EmotionAdjectiveCategory")
    fun getAdjectiveCategories(): List<EmotionAdjectiveCategory>

    @Query("SELECT * FROM EmotionAdjective")
    fun getAdjectives(): List<EmotionAdjective>

    @Query("SELECT * FROM EmotionAdjectiveCategory WHERE adjectiveCategoryIdx = :categoryIdx")
    fun getAdjectiveCategory(categoryIdx: Int): EmotionAdjectiveCategory

    @Query("SELECT adjectiveCategoryName FROM EmotionAdjectiveCategory WHERE adjectiveCategoryIdx = :categoryIdx")
    fun getAdjectiveCategoryName(categoryIdx: Int): String

//    @Query("SELECT * FROM EmotionAdjectiveCategory WHERE adjectiveCategoryIdx = :categoryIdx")
//    fun getDiaryCategoryAdjectives(categoryIdx: Int): List<EmotionAdjective>

    @Query("SELECT * FROM EmotionAdjective ORDER BY random() LIMIT 4")
    fun getRandom4Adjective(): List<EmotionAdjective>
}