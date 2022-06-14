package com.rk.assignmentaspire.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.data.NamesItem

class StudentDetails(private var namesItem: NamesItem) : Fragment() {


    companion object {
        fun newInstance(namesItem: NamesItem) = StudentDetails(namesItem = namesItem)
    }

    private lateinit var viewModel: StudentDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[StudentDetailsViewModel::class.java]

        view.findViewById<TextView>(R.id.name).text = namesItem.name
        view.findViewById<TextView>(R.id.marks).text = namesItem.marks.toString()
        view.findViewById<TextView>(R.id.id).text = namesItem.serialnumber.toString()

    }

}