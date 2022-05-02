package com.UXUI.todayim

import android.os.Bundle
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.databinding.ActivityResultBinding

class ResultActivity: BaseActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
    }

}