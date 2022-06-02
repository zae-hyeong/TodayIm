package com.UXUI.todayim.ui.analyze

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.UXUI.todayim.R
import com.UXUI.todayim.base.TEST_REPEAT_NUM
import com.UXUI.todayim.database.DiaryDatabase
import com.UXUI.todayim.database.EmotionAdjectiveCategory
import com.UXUI.todayim.database.EmotionAdjectiveDatabase
import com.UXUI.todayim.databinding.FragmentAnalyzeBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AnalyzeFragment : Fragment() {

    private var _binding: FragmentAnalyzeBinding? = null
    private val binding get() = _binding!!

    private lateinit var diaryDatabase: DiaryDatabase
    private lateinit var emotionDatabase: EmotionAdjectiveDatabase

    private val progressBarInterval: Float = 100F/(TEST_REPEAT_NUM.toFloat())

    private var categories: List<EmotionAdjectiveCategory>? = null
    private var weeklyEmotion: IntArray? = null

    private var emojiArray = arrayOf(
        getString(R.string.emoji_happy),
        getString(R.string.emoji_comfortable),
        getString(R.string.emoji_pain),
        getString(R.string.emoji_sad),
        getString(R.string.emoji_scary),
        getString(R.string.emoji_hate),
        getString(R.string.emoji_angry)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setInitialize(inflater, container)

        categories = getCategoriesFromDB()
        weeklyEmotion = getWeeklyEmotionFromDB()

        setAnalyzeProgressbar()

        return binding.root
    }

    private fun setInitialize(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        _binding = FragmentAnalyzeBinding.inflate(inflater, container, false)
        diaryDatabase = DiaryDatabase.getInstance(requireContext())!!
        emotionDatabase = EmotionAdjectiveDatabase.getInstance(requireContext())!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCategoriesFromDB(): List<EmotionAdjectiveCategory> {
        return emotionDatabase.emotionAdjectiveDao().getAdjectiveCategories()
    }


    private fun getWeeklyEmotionFromDB(): IntArray  {
        var emotionResult = intArrayOf(0, 0, 0, 0, 0, 0, 0)
        var diaryIdx: Int? = null

        var today = SimpleDateFormat("yyyy-MM-dd").format(Date())
        var prevDay: String

        var aYear: String
        var aMonth: String
        var aDay: Int

        var aDate: List<String>

        var i = 0
        var j = 0
        while( i < 7 ) {
            diaryIdx = diaryDatabase.diaryDao().getDiaryIdx(today)
            if( diaryIdx != null ) {
                val temp = diaryDatabase.diaryDao().getCategoryAdjectiveNum(diaryIdx) //todo 얘 문제 있음 -> 정확한 카테고리에 값 안들어감

                while( j < temp.size ) {
                    emotionResult[j] = emotionResult[j] + temp[j]
                    j++
                }
                aDate = today.split("-")

                aYear = aDate[0]
                aMonth = aDate[1]
                aDay = aDate[2].toInt()

                today = aYear + "년 " + aMonth + "월 " + (aDay-1).toString() + "일"
            }

            i++
        }

        return emotionResult
    }

    private fun setAnalyzeProgressbar() {
        var i = 0

        var percentage: Float = 0F

        while ( i < weeklyEmotion!!.size ) {
            val layoutInflater = requireActivity().getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val categoryProgress = layoutInflater.inflate(R.layout.layout_result_progressbar, null)

            percentage = weeklyEmotion!![i] * progressBarInterval //todo 인터벌 값이 result랑은 다름 -> 변경 필요

            categoryProgress.findViewById<TextView>(R.id.layout_result_category_tv).text =
                categories!![i].adjectiveCategoryName

            categoryProgress.findViewById<ProgressBar>(R.id.layout_result_progressbar)
                .progress = percentage.toInt()

            categoryProgress.findViewById<TextView>(R.id.layout_result_percentage_tv)
                .text =  String.format("%.1f%%", percentage)

            binding.analyzeEmotionLinearLayout.addView(categoryProgress)

            i++
        }
        var max = weeklyEmotion!![0]
        var maxIdx = 0
        for(i in 0..weeklyEmotion!!.size) {
            if (weeklyEmotion!![i] > max) {
                max = weeklyEmotion!![i];
                maxIdx = i
            }
        }

        binding.analyzeEmojiTv.text = emojiArray[maxIdx]

        binding.analyzeEmotionNameTv.text = categories!![maxIdx].adjectiveCategoryName
    }
}