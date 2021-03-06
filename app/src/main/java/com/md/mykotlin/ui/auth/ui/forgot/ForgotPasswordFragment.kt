package com.md.mykotlin.ui.auth.ui.forgot

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.md.mykotlin.R
import com.md.mykotlin.base.BaseFragment
import com.md.mykotlin.utils.CommonUtils
import com.md.mykotlin.utils.ViewUtil
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.layout_registration_header.*

class ForgotPasswordFragment : BaseFragment(), View.OnClickListener {

    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel

    override fun getLayoutRes(): Int {
        return R.layout.fragment_forgot_password
    }

    override fun initializeComponent(view: View?) {
        // set screen Title
        headerTextTitle.text = getString(R.string.screen_forgot_password)

        setupUI()

        forgotPasswordViewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        // Update data to viewModel
        editEmail.afterTextChanged {
            inputEmail.isErrorEnabled = false
            forgotPasswordViewModel.forgotPasswordRequestModel.email = it
        }

        // Show/Hide loader based on observer Boolean
        forgotPasswordViewModel.isLoading().observe(this, Observer {
            if (it)
                showProgressDialog(activity?.supportFragmentManager)
            else
                dismissProgressDialog()
        })
    }

    private fun setupUI() {
        ViewUtil.animateView(imageLogo, 300, 500)
        ViewUtil.animateView(inputEmail, 300, 500)
        ViewUtil.animateView(buttonSubmit, 500, 500)

        // set onClick
        headerImageBack.setOnClickListener(this)
        buttonSubmit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.headerImageBack -> requireActivity().onBackPressed()
            R.id.buttonSubmit -> {
                when {
                    validateData() -> {
                        context?.let { forgotPasswordViewModel.apiCallForgotPassword(it) }
                    }
                }
            }
        }
    }

    private fun validateData(): Boolean {
        return when {
            TextUtils.isEmpty(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                inputEmail.error = getString(R.string.err_please_enter_email_address)
                false
            }
            !CommonUtils.isValidEmailAddress(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                inputEmail.error = getString(R.string.err_please_enter_valid_email_address)
                false
            }
            else -> true
        }
    }
}