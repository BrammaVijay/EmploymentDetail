package com.example.employmentdetailactivity.model

import com.example.employmentdetailactivity.model.Address
import com.example.employmentdetailactivity.model.Company
import java.io.Serializable

data class EmployeDetailItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
): Serializable