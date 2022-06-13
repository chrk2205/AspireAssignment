package com.rk.assignmentaspire.api

import com.rk.assignmentaspire.data.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface{
    @GET("/api/v1/get_student_details/")
    fun getStudents(@Query("format") format : String, @Query("next_page") page: Int ): Call<DataModel>

}