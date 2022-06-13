package com.rk.assignmentaspire.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.assignmentaspire.Repository
import com.rk.assignmentaspire.data.DataModel

class MainViewModel : ViewModel() {


    private var repo: Repository? = null
    var postStudentsList: MutableLiveData<DataModel?>

    init {
        repo = Repository()
        postStudentsList = MutableLiveData()
    }

    fun getStudentsOfPage(page: Int) {
        postStudentsList = repo?.fetchStudentsInPage(pageNumber = page)!!
    }

}