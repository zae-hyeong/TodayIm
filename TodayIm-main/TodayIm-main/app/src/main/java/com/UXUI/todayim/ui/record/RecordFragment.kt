package com.UXUI.todayim.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.UXUI.todayim.R
import com.UXUI.todayim.databinding.FragmentRecordBinding
import com.UXUI.todayim.ui.calendarView.CalendarViewFragment
import com.UXUI.todayim.ui.listView.ListViewFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


class RecordFragment : Fragment() {

    private var _binding: FragmentRecordBinding? = null

    private val binding get() = _binding!!

    private val tabMenu = arrayListOf("Calendar", "List")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val v: View = binding.root

        val viewPageAdapter = VPAdapter(this)
        binding.recordViewPager.adapter = viewPageAdapter

        TabLayoutMediator(binding.recordTabLayout, binding.recordViewPager) {
            tab, position -> tab.text = tabMenu[position]
        }.attach()

        return v
    }
}