package com.example.employmentdetailactivity.repository

import com.example.employmentdetailactivity.model.dataClass.EmployeDetailApi
import javax.inject.Inject

class EmploymentRepository @Inject constructor(private val api: EmployeDetailApi) {

    suspend fun getEmployerApi() = api.getEmployerAPi()

    suspend fun secondEmployerApi(id: Int) = api.secondEmployeeDetail(id)
}