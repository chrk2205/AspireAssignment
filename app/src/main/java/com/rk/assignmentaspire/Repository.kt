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


    private lateinit var apiInterface: ApiInterface

    fun fetchStudentsInPage(pageNumber: Int): MutableLiveData<DataModel?> {
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
                    } else {
                        data.value = null
                    }
                    Log.e("rks","pass $res")
                }
            })
        return data
    }
}