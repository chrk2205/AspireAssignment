package com.rk.assignmentaspire.adapters

import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.data.NamesItem

class StudentViewAdapter(studentsList: List<NamesItem>) :
    RecyclerView.Adapter<StudentViewAdapter.ViewHolder>() {

    var onItemClick: ((NamesItem) -> Unit)? = null
    private var list: List<NamesItem> = studentsList
    private var updatedTimes: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_view_name_marks, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list[position].serialnumber.toString()
        holder.name.text = list[position].name
        holder.mark.text = Editable.Factory.getInstance().newEditable(list[position].marks.toString())
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(updateList: List<NamesItem>) {
        list = list + updateList
        notifyItemRangeInserted(updatedTimes * 100, (updatedTimes + 1) * 100)
        updatedTimes++
    }


    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val id: TextView = ItemView.findViewById(R.id.id)
        val name: TextView = ItemView.findViewById(R.id.name)
        val mark: EditText = ItemView.findViewById(R.id.marks)

        init {

            ItemView.setOnClickListener{
                list[adapterPosition].marks = mark.text.toString().toInt()
                onItemClick?.invoke(list[adapterPosition])
            }

        }

    }


}