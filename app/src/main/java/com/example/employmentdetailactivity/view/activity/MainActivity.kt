package com.example.employmentdetailactivity.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.employmentdetailactivity.NetworkResult
import com.example.employmentdetailactivity.R
import com.example.employmentdetailactivity.databinding.ActivityMainBinding
import com.example.employmentdetailactivity.model.EmployeDetail
import com.example.employmentdetailactivity.model.EmployeDetailItem
import com.example.employmentdetailactivity.view.activity.adapter.EmployeAdapter
import com.example.employmentdetailactivity.viewmodel.EmployerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val employeViewmodel by viewModels<EmployerViewModel>()

    private val employeAdapter by lazy { EmployeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
        setOInObserver()
        setCallBackFunction()
        employeViewmodel.getEmployeDetail()
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = employeAdapter
    }

    private fun setOInObserver() {
        lifecycleScope.launchWhenStarted {

            employeViewmodel.response.collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        employeAdapter.differ.submitList(response.data)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setCallBackFunction() {

        employeAdapter.itemViewClickListener {
            openEmployeeDetails(it.id)
        }

    }

    private fun openEmployeeDetails(id: Int) {
        val intent = Intent(this@MainActivity, EmployerSecondActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}