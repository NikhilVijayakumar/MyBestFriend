package com.nikhil.mybestfriend.feature.auth.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.auth.login.viewmodel.LoginViewModelFactory
import com.nikhil.mybestfriend.feature.auth.register.viewmodel.RegisterViewModel
import com.nikhil.mybestfriend.feature.auth.register.viewmodel.RegisterViewModelFactory
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.utils.*
import com.nikhil.mybestfriend.feature.commons.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class RegisterFragment : BaseFragment() {

    private val factory: RegisterViewModelFactory by instance<RegisterViewModelFactory>()
    private lateinit var viewmodel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun initFragment() {
        initData()
        initButtons()
    }

    private fun initData() {
        viewmodel = ViewModelProviders.of(this, factory)
            .get(RegisterViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        viewmodel.status.observe(this@RegisterFragment, Observer { data ->
            data?.let { status ->
                when (status) {
                    RepoStatus.COMPLETED -> {
                        gotoLogin()
                    }
                    RepoStatus.LOADING -> {

                    }
                    RepoStatus.ERROR -> {
                        context?.let {
                            showSnackBar(it.getString(R.string.login_invalid))
                        }

                    }
                    RepoStatus.DATABASE_EXCEPTION -> {
                        context?.let {
                            showSnackBar(it.getString(R.string.database_exception))
                        }
                    }
                    RepoStatus.API_EXCEPTION -> {
                    }
                }
            }
        })
    }

    private fun showSnackBar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }

    }

    private fun initButtons() = registerButton.setOnClickListener {

        var valid = passwordTextInputLayout.isValidPassword()
        valid = emailTextInputLayout.isValidEmail() && valid
        if (valid) {
            registerUser()
        }
    }

    private fun registerUser() = launch {
        viewmodel.register(
            UserEntity(
                emailEditText.data,
                passwordEditText.data
            )
        )
    }

    private fun gotoLogin() {
        val direction: NavDirections =
            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment4()
        Navigation.findNavController(registerButton).navigate(direction)

    }


}
