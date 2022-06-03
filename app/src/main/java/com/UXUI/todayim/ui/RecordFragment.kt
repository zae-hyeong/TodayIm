package com.UXUI.todayim.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.UXUI.todayim.R
import com.UXUI.todayim.databinding.FragmentRecordBinding
import com.UXUI.todayim.ui.calendarView.CalendarViewFragment
import com.UXUI.todayim.ui.listView.ListViewFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class RecordFragment : Fragment() {

    private var _binding: FragmentRecordBinding? = null

    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val v: View = binding.root

        tabLayout = v.findViewById(R.id.record_tablayout) as TabLayout
        viewPager = v.findViewById(R.id.record_viewPager) as ViewPager2

        viewPager.offscreenPageLimit = 2 //현재 페이지의 양쪽에 보유해야하는 페이지 수를 설정 (상황에 맞게 사용하시면 됩니다.)

        tabLayout.setupWithViewPager(viewPager) //텝레이아웃과 뷰페이저를 연결

        viewPager.adapter = PageAdapter(childFragmentManager, requireContext()) //뷰페이저 어뎁터 설정 연결


        binding.recordTablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) { // 선택 X -> 선택 O
                if (tab.position == 0) { //탭레이아웃 포지션 얻기 0 이 Tab 1


                } else if (tab.position == 1) {


                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) { // 선택 O -> 선택
            }

            override fun onTabReselected(tab: TabLayout.Tab) { // 선택 O -> 선택 O
            }
        })

        return v
    }

    internal class PageAdapter (fm: FragmentManager?, context: Context?): FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return if (position == 0) { //프래그먼트 사용 포지션 설정 0 이 첫탭
                CalendarViewFragment()
            } else {
                ListViewFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence? {
            return if (position == 0) { //텝 레이아웃의 타이틀 설정
                "Calendar"
            } else {
                "List"
            }
        }
    }
}