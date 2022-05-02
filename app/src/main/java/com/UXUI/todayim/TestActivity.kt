package com.UXUI.todayim

import android.os.Bundle
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.databinding.ActivitySplashBinding
import com.UXUI.todayim.databinding.ActivityTestBinding

class TestActivity: BaseActivity() {
    lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
    }
}