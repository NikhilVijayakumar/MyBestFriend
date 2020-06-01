package com.nikhil.mybestfriend.feature.splash.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.BounceInterpolator
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.commons.view.BaseActivity
import com.nikhil.mybestfriend.feature.auth.login.view.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    val ANIMATION_DURATION: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimation()
    }

    private fun startAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = ANIMATION_DURATION

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            imageView.scaleX = value
            imageView.scaleY = value
        }

        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                gotoLoginActivity()
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
        valueAnimator.start()
    }


    private fun gotoLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
