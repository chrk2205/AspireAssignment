package com.rk.assignmentaspire.adapters

import android.content.SharedPreferences
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.data.NamesItem

class StudentViewAdapter(studentsList: List<NamesItem>,
                         private val sharedPreferences: SharedPreferences
) :
    RecyclerView.Adapter<StudentViewAdapter.ViewHolder>() {

    var onItemClick: ((NamesItem) -> Unit)? = null
    private var list: List<NamesItem> = studentsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_view_name_marks, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list[position].serialnumber.toString()
        holder.name.text = list[position].name
        list[position].marks = sharedPreferences.getInt(position.toString(),0)
        holder.mark.text = Editable.Factory.getInstance().newEditable(list[position].marks.toString())
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(updateList: List<NamesItem>) {
        list = updateList
        val k : Int = list.size
        notifyItemRangeInserted(k-100,100)
    }

    fun getStudentViewList() : List<NamesItem>{
        return list
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val id: TextView = ItemView.findViewById(R.id.id)
        val name: TextView = ItemView.findViewById(R.id.name)
        val mark: EditText = ItemView.findViewById(R.id.marks)

        init {
            mark.doAfterTextChanged {
                try {
                    list[position].marks = it.toString().toInt()
                    with(sharedPreferences.edit()){
                        putInt(position.toString(),list[position].marks)
                        apply()
                    }
                }catch (e : NumberFormatException){
                    Log.e("rks","exception converting $e")
                }
            }
            ItemView.setOnClickListener{
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }


}