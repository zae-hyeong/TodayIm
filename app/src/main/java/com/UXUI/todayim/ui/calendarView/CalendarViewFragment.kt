package com.UXUI.todayim.ui.calendarView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.UXUI.todayim.R
import com.UXUI.todayim.database.Diary
import com.UXUI.todayim.database.DiaryDatabase
import com.UXUI.todayim.databinding.FragmentViewCalendarBinding
import com.UXUI.todayim.ui.version.VersionViewModel
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarViewFragment : Fragment(), View.OnClickListener, OnDayClickListener {

    private var _binding: FragmentViewCalendarBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // room database
    private lateinit var roomDatabase: DiaryDatabase

    // current clicked diary item
    private var clickDiaryItem: Diary? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(VersionViewModel::class.java)

        _binding = FragmentViewCalendarBinding.inflate(inflater, container, false)

        // room data base init
        roomDatabase = DiaryDatabase.getInstance(binding.root.context)!!

        loadRecentCalendarData()

        binding.btnModify.setOnClickListener(this)
        binding.btnRemove.setOnClickListener(this)
        binding.calendarView.setOnDayClickListener(this)

        return binding.root
    }

    private fun loadRecentCalendarData() {
        // load recent diary save data to calendar
        CoroutineScope(Dispatchers.IO).launch {
            val events = arrayListOf<EventDay>()
            val diaryList = roomDatabase.diaryDao().getAllDiaryData()

            for (diaryItem in diaryList) {
                val calendar = Calendar.getInstance()
                try {
                    calendar.time = SimpleDateFormat("yyyy-MM-dd").parse(diaryItem.diaryDate) as Date
                    events.add(EventDay(calendar, R.drawable.shape_calendar_dot))
                } catch (e : ParseException) {
                    e.printStackTrace()
                }
            }
            requireActivity().runOnUiThread {
                binding.calendarView.setEvents(events)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDayClick(eventDay: EventDay?) {
        CoroutineScope(Dispatchers.IO).launch {
            clickDiaryItem = roomDatabase.diaryDao().getFindSpecificData(SimpleDateFormat("yyyy-MM-dd").format(eventDay!!.calendar.time))
            if (clickDiaryItem == null) {
                requireActivity().runOnUiThread {
                    binding.btnModify.visibility = View.INVISIBLE
                    binding.btnRemove.visibility = View.INVISIBLE
                    binding.tvClickDate.setText("")
                    binding.etContent.setText("")
                }
                return@launch
            }

            requireActivity().runOnUiThread {
                binding.btnModify.visibility = View.VISIBLE
                binding.btnRemove.visibility = View.VISIBLE
                binding.tvClickDate.setText(clickDiaryItem!!.diaryDate)
                binding.etContent.setText(clickDiaryItem!!.diaryComment)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_modify -> {
                // 수정 하기

                if (clickDiaryItem == null)
                    return

                CoroutineScope(Dispatchers.IO).launch {
                    clickDiaryItem!!.diaryComment = binding.etContent.text.toString()
                    roomDatabase.diaryDao().updateDiaryInfo(clickDiaryItem!!)
                    loadRecentCalendarData()
                    requireActivity().runOnUiThread {
                        Toast.makeText(binding.root.context, "수정이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            R.id.btn_remove -> {
                // 삭제 하기

                if (clickDiaryItem == null)
                    return

                CoroutineScope(Dispatchers.IO).launch {
                    roomDatabase.diaryDao().deleteDiaryInfo(clickDiaryItem!!)
                    loadRecentCalendarData()
                    requireActivity().runOnUiThread {
                        binding.btnModify.visibility = View.INVISIBLE
                        binding.btnRemove.visibility = View.INVISIBLE
                        binding.tvClickDate.setText("")
                        binding.etContent.setText("")
                        Toast.makeText(binding.root.context, "삭제가 완료 되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}