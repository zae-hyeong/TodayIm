package com.UXUI.todayim.ui.listView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UXUI.todayim.Record
import com.UXUI.todayim.Records
import com.UXUI.todayim.databinding.ItemRecordListBinding

class RecordRV : RecyclerView.Adapter<RecordRV.RecordViewHolder>() {

    private lateinit var recordClickListener : RecordClickListener

    interface RecordClickListener{
        fun onItemClick() //실행해줄 함수(데이터 랜더링을 위해 앨범 정보를 받아왔음!)
    }

    inner class RecordViewHolder(private val binding: ItemRecordListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(record: Record) {
            binding.itemRecordBodyTv.text= record.description
            binding.itemRecordDateTv.text= record.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding: ItemRecordListBinding = ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(Records[position])
        //TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = Records.size

    fun setRecordClickListener(itemClickListener: RecordClickListener) {
        recordClickListener = itemClickListener
    }


}