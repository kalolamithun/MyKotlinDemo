package com.md.mykotlin.ui.auth.ui.login

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.md.mykotlin.R
import com.md.mykotlin.base.BaseFragment
import com.md.mykotlin.ui.main.HomeActivity
import com.md.mykotlin.utils.CommonUtils
import com.md.mykotlin.utils.LogUtil
import com.md.mykotlin.utils.ViewUtil
import com.md.mykotlin.utils.preferences.PreferenceConstant
import com.md.mykotlin.utils.preferences.putBoolean
import com.md.mykotlin.utils.toMD5
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_registration_header.*


class LoginFragment : BaseFragment(), View.OnClickListener {

    private lateinit var loginViewModel: LoginViewModel

    override fun getLayoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun initializeComponent(view: View?) {
        // set screen Title
        headerTextTitle.text = getString(R.string.screen_login)

        setupUI()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Update data to viewModel
        updateData()

        setObserver()
    }

    private fun setObserver() {
        // Show/Hide loader based on observer Boolean
        loginViewModel.isLoading().observe(this, Observer {
            if (it)
                showProgressDialog(activity?.supportFragmentManager)
            else
                dismissProgressDialog()
        })

        loginViewModel.getUserData().observe(viewLifecycleOwner, Observer {

            it?._id?.let { userId -> LogUtil.d("User userId: ", userId) }
            it?.name?.let { name -> LogUtil.d("User name: ", name) }
            it?.emailId?.let { email -> LogUtil.d("User email: ", email) }
            LogUtil.d("User mobileNumber: ", "" + (it?.mobileNumber ?: ""))

            // Added isUserLogin true in preference
            PreferenceConstant.isUserLogin.putBoolean(true)
        })
    }

    private fun updateData() {
        editEmail.afterTextChanged {
            inputEmail.isErrorEnabled = false
            loginViewModel.loginRequestModel.emailId = it
        }
        editPassword.afterTextChanged {
            inputPassword.isErrorEnabled = false
            loginViewModel.loginRequestModel.password =
                it.toMD5() // converted input to MD5 encryption
        }
    }

    private fun setupUI() {
        ViewUtil.animateView(imageLogo, 300, 500)
        ViewUtil.animateView(inputEmail, 300, 500)
        ViewUtil.animateView(inputPassword, 300, 500)
        ViewUtil.animateView(textForgotPassword, 500, 500)
        ViewUtil.animateView(buttonSubmit, 500, 500)
        ViewUtil.animateView(textSignUp, 500, 500)

        //set spannable text
        CommonUtils.applySpanPrimaryColor(
            textSignUp,
            getString(R.string.str_don_t_have_an_account),
            getString(R.string.str_sign_up)
        )

        // set onClick
        headerImageBack.setOnClickListener(this)
        textForgotPassword.setOnClickListener(this)
        textSignUp.setOnClickListener(this)
        buttonSubmit.setOnClickListener(this)
        textSkip.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.headerImageBack -> requireActivity().onBackPressed()
            R.id.textSkip -> {
                // Added isUserLogin true in preference
                PreferenceConstant.isUserLogin.putBoolean(true)

                requireActivity().startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
            R.id.textForgotPassword -> Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            R.id.textSignUp -> Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_signUpFragment)
            R.id.buttonSubmit -> {
                when {
                    validateData() -> {
                        loginViewModel.apiCallLogin()
                    }
                }
            }
        }
    }

    private fun validateData(): Boolean {
        when {
            TextUtils.isEmpty(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                inputEmail.error = getString(R.string.err_please_enter_email_address)
                return false
            }
            !CommonUtils.isValidEmailAddress(editEmail.text.toString().trim()) -> {
                editEmail.requestFocus()
                ViewUtil.animateView(inputEmail, 10, 200)
                inputEmail.error = getString(R.string.err_please_enter_valid_email_address)
                return false
            }
            TextUtils.isEmpty(editPassword.text.toString().trim()) -> {
                editPassword.requestFocus()
                ViewUtil.animateView(inputPassword, 10, 200)
                inputPassword.error = getString(R.string.err_please_enter_password)
                return false
            }
            else -> return true
        }
    }
}