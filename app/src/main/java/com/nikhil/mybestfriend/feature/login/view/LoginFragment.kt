package com.nikhil.mybestfriend.feature.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun initFragment() {
        initButtons()
    }

    private fun initButtons() {
        registerButton.setOnClickListener {
            gotoRegister()

        }

        loginbutton.setOnClickListener {
           gotoHome()
        }

    }

    private fun gotoHome() {
        val direction:NavDirections = LoginFragmentDirections.actionLoginFragmentToCatListFragment()
        Navigation.findNavController(loginbutton).navigate(direction)
    }

    private fun gotoRegister() {
        val direction:NavDirections = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        Navigation.findNavController(registerButton).navigate(direction)
    }
}
