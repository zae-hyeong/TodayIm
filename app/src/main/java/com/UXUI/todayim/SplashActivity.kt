package com.UXUI.todayim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.database.EmotionCategory
import com.UXUI.todayim.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        Handler(Looper.getMainLooper()).postDelayed({
            //액티비티 이동
            startMainActivity()
        }, DURATION)
    }

    companion object {
        private const val DURATION : Long = 1500
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

//    private fun addDummyEmotions() {
//        EmotionCategory(
//            diaryIdx = 0,
//            categoryName = "편안함",
//            emotions = arrayOf()
//        )
//    }
}