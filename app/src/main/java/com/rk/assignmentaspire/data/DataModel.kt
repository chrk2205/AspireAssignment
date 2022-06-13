package com.rk.assignmentaspire.data

data class DataModel(val nextPage: Int = -1,
                     val statusCode: Int = 0,
                     val data: Data,
                     val success: Boolean = false,
                     val message: String = "",
                     val executionTime: String = "")