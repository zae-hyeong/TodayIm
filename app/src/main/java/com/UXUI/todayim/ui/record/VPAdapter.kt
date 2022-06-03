package com.UXUI.todayim.ui.record

import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.UXUI.todayim.ui.calendarView.CalendarViewFragment
import com.UXUI.todayim.ui.listView.ListViewFragment


class VPAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CalendarViewFragment()
            else -> ListViewFragment()
        }
    }

}