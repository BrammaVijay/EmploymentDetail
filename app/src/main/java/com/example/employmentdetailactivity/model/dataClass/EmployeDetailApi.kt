package com.example.employmentdetailactivity.model.dataClass

import com.example.employmentdetailactivity.model.EmployeDetail
import com.example.employmentdetailactivity.model.EmployeDetailItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeDetailApi {

    @GET("users")
    suspend fun getEmployerAPi(): Response<EmployeDetail>

    @GET("users/{id}")
    suspend fun secondEmployeeDetail(@Path("id") id: Int): Response<EmployeDetailItem>

}