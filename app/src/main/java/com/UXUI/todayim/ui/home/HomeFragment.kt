package com.UXUI.todayim.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.UXUI.todayim.MainActivity
import com.UXUI.todayim.R
import com.UXUI.todayim.ResultActivity
import com.UXUI.todayim.TestActivity
import com.UXUI.todayim.databinding.FragmentHomeBinding
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment(), View.OnClickListener, View.OnAttachStateChangeListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.homeMainTv
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.homeStartBtn.addOnAttachStateChangeListener(this)
        binding.homeStartPb.progress = 0

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewAttachedToWindow(p0: View?) {
        while(true){
            binding.homeStartPb.progress++
            sleep(100)
            if(binding.homeStartPb.progress >=100) {
                val startTestActivityIntent = Intent(activity, TestActivity::class.java)
                startActivity(startTestActivityIntent)
                break
            }
        }
    }

    override fun onViewDetachedFromWindow(p0: View?) {
        //TODO("Not yet implemented")
    }

    override fun onClick(p0: View?) {
        //TODO("Not yet implemented")
    }
}
