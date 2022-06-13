package com.rk.assignmentaspire

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rk.assignmentaspire.api.ApiClient
import com.rk.assignmentaspire.api.ApiInterface
import com.rk.assignmentaspire.data.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

const val format: String = "json"

class Repository {

    var count : Int = 0
    private lateinit var apiInterface: ApiInterface

    fun fetchStudentsInPage(pageNumber: Int): MutableLiveData<DataModel?> {
        count++
        Log.e("rks","count $count")
        val data = MutableLiveData<DataModel?>()
        apiInterface = ApiClient.getApiClient().create()
        apiInterface.getStudents(format = format, page = pageNumber)
            .enqueue(object : Callback<DataModel> {
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    data.value = null
                    Log.e("rks","fail $t")
                }
                override fun onResponse(
                    call: Call<DataModel>,
                    response: Response<DataModel>
                ) {
                    val res = response.body()
                    if (response.code() == 200 && res != null) {
                        data.value = res
                        Log.e("rks","success $count")
                    } else {
                        data.value = null
                    }
                }
            })
        return data
    }
}