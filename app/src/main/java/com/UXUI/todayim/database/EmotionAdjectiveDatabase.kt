package com.UXUI.todayim.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EmotionAdjectiveCategory::class, EmotionAdjective::class], version = 1)
abstract class EmotionAdjectiveDatabase : RoomDatabase() {
    abstract fun emotionAdjectiveDao(): EmotionAdjectiveDao

    // 싱글톤 구현을 위해 companion object로 생성
    companion object {
        private var instance: EmotionAdjectiveDatabase? = null
        @Synchronized
        fun getInstance(context: Context): EmotionAdjectiveDatabase? {
            if (instance == null) {
                synchronized(EmotionAdjectiveDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmotionAdjectiveDatabase::class.java,
                        "emotionAdjective-database"
                    ).build()
                }
            }
            return instance
        }
    }
}