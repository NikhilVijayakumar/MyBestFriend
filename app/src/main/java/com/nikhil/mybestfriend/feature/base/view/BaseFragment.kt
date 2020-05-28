package com.nikhil.mybestfriend.feature.base.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open  class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    open protected fun initFragment()  {

    }


}