package com.rk.assignmentaspire.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rk.assignmentaspire.Repository
import com.rk.assignmentaspire.data.DataModel


class MainViewModel : ViewModel() {


    private var repo: Repository? = Repository()

    var pageNumber: Int = -1


    fun getNextStudents(): LiveData<DataModel?> {
        pageNumber++
        return repo?.fetchStudentsInPage(pageNumber = pageNumber)!!
    }


}