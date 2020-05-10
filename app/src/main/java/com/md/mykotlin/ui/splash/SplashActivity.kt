package com.md.mykotlin.ui.splash

import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.md.mykotlin.R
import com.md.mykotlin.base.BaseActivity
import com.md.mykotlin.ui.auth.ui.RegistrationActivity
import com.md.mykotlin.ui.main.HomeActivity
import com.md.mykotlin.ui.main.MainActivity
import com.md.mykotlin.utils.preferences.PreferenceConstant
import com.md.mykotlin.utils.preferences.getBoolean
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity(), Animation.AnimationListener {

    private fun performNavigation() {
        if (PreferenceConstant.isUserLogin.getBoolean()!!)
            startActivity(HomeActivity::class.java)
        else
            startActivity(RegistrationActivity::class.java)
        finish()
    }

    private fun executeHandler() {
        val delayMillis: Long = 3000
        /*
         * Handler is used to set some delay on this screen
         */
        Handler().postDelayed(Runnable { performNavigation() }, delayMillis)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun initializeComponents() {
        val loadAnimation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.transition_from_top)
        // set animation listener
        loadAnimation.setAnimationListener(this)
        // start the animation
        imageLogo.startAnimation(loadAnimation)
    }

    override fun onAnimationStart(animation: Animation) {}
    override fun onAnimationEnd(animation: Animation) {
        executeHandler()
    }

    override fun onAnimationRepeat(animation: Animation) {}
}