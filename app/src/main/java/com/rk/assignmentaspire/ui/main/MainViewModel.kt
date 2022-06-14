package com.rk.assignmentaspire.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rk.assignmentaspire.Repository
import com.rk.assignmentaspire.data.DataModel
import com.rk.assignmentaspire.data.NamesItem


class MainViewModel : ViewModel() {


    private var repo: Repository? = Repository()

    private var listOfStudent : List<NamesItem> = emptyList()

    private var pageNumber: Int = -1

    fun resetPageNumber(){
        pageNumber = -1
    }

    fun getNextStudents(): LiveData<DataModel?> {
        pageNumber++
        return repo?.fetchStudentsInPage(pageNumber = pageNumber)!!
    }

    fun getStudentsList(): List<NamesItem>{
        return listOfStudent
    }

    fun updateStudentsList( list : List<NamesItem> ){
        listOfStudent = listOfStudent + list
    }

}