package com.nikhil.mybestfriend.feature.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun initFragment() {
        initButtons()
    }

    private fun initButtons() {
        registerButton.setOnClickListener {
            val direction: NavDirections = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(direction)
        }

    }


}
