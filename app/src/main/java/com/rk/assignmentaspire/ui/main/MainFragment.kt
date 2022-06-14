package com.rk.assignmentaspire.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rk.assignmentaspire.R
import com.rk.assignmentaspire.adapters.StudentViewAdapter
import com.rk.assignmentaspire.data.NamesItem
import java.lang.reflect.Type


const val RECYCLERVIEW_LIST : String = "RECYCLERVIEW"

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: StudentViewAdapter
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!

        if(savedInstanceState!=null){
            val type: Type = object : TypeToken<List<NamesItem?>?>() {}.type
            val prevList : List<NamesItem> = Gson().fromJson(savedInstanceState.getString(RECYCLERVIEW_LIST,""),type)

            setUpRecyclerView(view.findViewById(R.id.studentView), prevList,context)
        }else
            setUpRecyclerView(view.findViewById(R.id.studentView), viewModel.getStudentsList(),context)

    }



    private fun setUpRecyclerView(recyclerview: RecyclerView, studentList : List<NamesItem>,context: Context?) {
        adapter = StudentViewAdapter(studentList,sharedPref)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        if(studentList.isEmpty())
            loadNext()
        adapter.onItemClick = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, StudentDetails.newInstance(it))
                .addToBackStack("")
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
            viewModel.updateStudentsList(it?.data?.names!!)
            adapter.updateData(viewModel.getStudentsList())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RECYCLERVIEW_LIST,Gson().toJson(adapter.getStudentViewList()))
    }

}
