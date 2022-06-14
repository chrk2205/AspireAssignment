package com.rk.assignmentaspire.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rk.assignmentaspire.MainActivity
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.adapters.StudentViewAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: StudentViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setUpRecyclerView(view.findViewById(R.id.studentView), context)
    }



    private fun setUpRecyclerView(recyclerview: RecyclerView, context: Context?) {
        adapter = StudentViewAdapter(emptyList())
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        loadNext()

        adapter.onItemClick = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, StudentDetails.newInstance(it))
                .commit()
        }

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadNext()
                }
            }
        })

    }

    private fun loadNext() {
        viewModel.getNextStudents().observe(viewLifecycleOwner) {
            adapter.updateData(it?.data?.names!!)
        }
    }
}
