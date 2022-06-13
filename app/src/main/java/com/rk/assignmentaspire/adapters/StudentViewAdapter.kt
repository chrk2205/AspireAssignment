package com.rk.assignmentaspire.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.data.NamesItem

class StudentViewAdapter(private val studentsList: List<NamesItem>) :
    RecyclerView.Adapter<StudentViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_view_name_marks, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = studentsList[position].serialnumber.toString()
        holder.name.text = studentsList[position].name
        holder.mark.text = "NA"
    }

    override fun getItemCount(): Int {
        return studentsList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val name: TextView = itemView.findViewById(R.id.name)
        val mark: TextView = itemView.findViewById(R.id.marks)

    }



}