package com.md.mykotlin.ui.main.ui.home

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.md.database.models.REmployee
import com.md.mykotlin.R
import com.md.mykotlin.base.BaseFragment
import com.md.mykotlin.ui.main.model.Data
import com.md.mykotlin.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    companion object {
        public const val TAG: String = "HomeFragment"
    }

    private lateinit var homeViewModel: HomeViewModel
    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun initializeComponent(view: View?) {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        setObserver()
    }

    private fun setObserver() {
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            text_home.text = it
        })

        homeViewModel.isLoading().observe(this, Observer {
            if (it)
                showProgressDialog(activity?.supportFragmentManager)
            else
                dismissProgressDialog()
        })

        homeViewModel.getEmployeeData().observe(this, Observer {
            it?.size?.let { dataSize -> LogUtil.i(TAG, "Data Size API: $dataSize") }
            it.forEach { data: REmployee ->
                data.id.let { id -> LogUtil.i(TAG, "Employees ID: $id") }
                data.empName.let { name -> LogUtil.i(TAG, "Employees Name: $name") }
                data.empAge.let { age -> LogUtil.i(TAG, "Employees Age: $age") }
                data.empSalary.let { salary -> LogUtil.i(TAG, "Employees Salary: $salary") }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get Employees
        homeViewModel.apiGetEmployee()
    }
}
