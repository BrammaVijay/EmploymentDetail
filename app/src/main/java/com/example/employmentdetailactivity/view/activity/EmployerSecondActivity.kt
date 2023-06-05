package com.example.employmentdetailactivity.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.employmentdetailactivity.NetworkResult
import com.example.employmentdetailactivity.databinding.ActivityEmployerSecondBinding
import com.example.employmentdetailactivity.model.EmployeDetailItem
import com.example.employmentdetailactivity.viewmodel.EmployerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployerSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployerSecondBinding

    private val secondViewModel by viewModels<EmployerViewModel>()

    private lateinit var passData: EmployeDetailItem

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmployerSecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getTheValues()
        setOnEmailClickListener()
        setOnPhoneNumberClikListener()
        observer()
    }

    private fun getTheValues() {
        intent.extras?.let { id ->
            val employeeId = id.getInt("id")
            secondViewModel.secondEmployeeDetail(employeeId)
        }
    }


    private fun observer() {
        lifecycleScope.launch(Dispatchers.Main) {
            secondViewModel.response2.collectLatest {
                when (it) {
                    is NetworkResult.Success -> {
                        passData = it.data!!
                        loadEmployeeDetails(passData)
                    }

                    else -> {}
                }
            }
        }
    }
    private fun loadEmployeeDetails(passData: EmployeDetailItem) {
        binding.apply {
            employeId.text = passData.id.toString()
            empName.text = passData.name
            email.text = passData.email.lowercase()
            Address.text = passData.address.toString()
            phoneNumber.text = passData.phone
            companyDetail.text = passData.company.name
        }
    }


    private fun setOnEmailClickListener() {
        binding.email.setOnClickListener {
            val email = binding.email.text.toString()
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:$email")
            startActivity(intent)
        }
    }

    private fun setOnPhoneNumberClikListener() {
        binding.phoneNumber.setOnClickListener {
            val phoneNumber = binding.phoneNumber.text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }

    }
}

