package com.rk.assignmentaspire.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.adapters.StudentViewAdapter
import com.rk.assignmentaspire.data.NamesItem

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getStudentsOfPage(0)

        viewModel.postStudentsList.observe(viewLifecycleOwner) {
            setView(it?.data?.names!!)
        }


    }

    private fun setView(listOfStudents: List<NamesItem>) {
        val recyclerview = view?.findViewById<RecyclerView>(R.id.studentView)
        recyclerview?.layoutManager = LinearLayoutManager(context)
        val adapter = StudentViewAdapter(listOfStudents)
        recyclerview?.adapter = adapter
    }

}