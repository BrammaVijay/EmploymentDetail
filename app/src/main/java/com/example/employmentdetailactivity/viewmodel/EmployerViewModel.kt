package com.example.employmentdetailactivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employmentdetailactivity.NetworkResult
import com.example.employmentdetailactivity.model.EmployeDetail
import com.example.employmentdetailactivity.model.EmployeDetailItem
import com.example.employmentdetailactivity.repository.EmploymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployerViewModel @Inject constructor(val repository:EmploymentRepository):ViewModel() {

    private val _response:MutableStateFlow<NetworkResult<EmployeDetail>> = MutableStateFlow(NetworkResult.Loading())
    val response:StateFlow<NetworkResult<EmployeDetail>> = _response

    private val _response2:MutableStateFlow<NetworkResult<EmployeDetailItem>> = MutableStateFlow(NetworkResult.Loading())
    val response2:StateFlow<NetworkResult<EmployeDetailItem>> = _response2


    fun getEmployeDetail(){

        viewModelScope.launch(Dispatchers.Main) {
            _response.value=NetworkResult.Loading()

            val result= repository.getEmployerApi()

            if (result.isSuccessful){

                val list=result.body()
                println("secondEmployeDetail >>> ${result.body()}")

                _response.value = list?.let { NetworkResult.Success(it) }!!
            }
        }
    }

    fun secondEmployeeDetail(id:Int){


        viewModelScope.launch(Dispatchers.IO) {
            val result= repository.secondEmployerApi(id)
            println("secondEmployeeDetail >>> ${result.body()}")

            if (result.isSuccessful){
                _response2.value = NetworkResult.Loading()
                _response2.value = result.body()?.let { NetworkResult.Success(it) }!!
            }
        }
    }
}