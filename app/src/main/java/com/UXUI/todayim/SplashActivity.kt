package com.UXUI.todayim

import android.os.Bundle
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.databinding.ActivitySplashBinding

class SplashActivity: BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
    }
}