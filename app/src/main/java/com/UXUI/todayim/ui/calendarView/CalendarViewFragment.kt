package com.UXUI.todayim.ui.calendarView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.UXUI.todayim.databinding.FragmentViewCalendarBinding
import com.UXUI.todayim.ui.version.VersionViewModel

class CalendarViewFragment : Fragment() {

    private var _binding: FragmentViewCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(VersionViewModel::class.java)

        _binding = FragmentViewCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.calendarViewMainTv
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}