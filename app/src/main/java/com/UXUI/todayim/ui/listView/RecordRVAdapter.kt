package com.UXUI.todayim.ui.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UXUI.todayim.database.Diary
import com.UXUI.todayim.databinding.ItemRecordListBinding

class RecordRVAdapter : RecyclerView.Adapter<RecordRVAdapter.RecordViewHolder>() {

    private lateinit var recordClickListener : RecordClickListener

    interface RecordClickListener{
        fun onItemClick(diary: Diary) //실행해줄 함수(데이터 랜더링을 위해 앨범 정보를 받아왔음!)
    }

    inner class RecordViewHolder(private val binding: ItemRecordListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(diary: Diary) {
            binding.itemRecordBodyTv.text= diary.diaryComment
            binding.itemRecordDateTv.text= diary.diaryDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding: ItemRecordListBinding = ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(records[position])
        holder.itemView.setOnClickListener {
            recordClickListener.onItemClick(records[position])
        }
    }

    override fun getItemCount(): Int = records.size

    fun setRecordClickListener(itemClickListener: RecordClickListener) {
        recordClickListener = itemClickListener
    }


}