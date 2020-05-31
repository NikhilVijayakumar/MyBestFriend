package com.nikhil.mybestfriend.feature.auth.login.view

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
import com.nikhil.mybestfriend.feature.auth.login.viewmodel.LoginViewModel
import com.nikhil.mybestfriend.feature.auth.login.viewmodel.LoginViewModelFactory
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.utils.data
import com.nikhil.mybestfriend.feature.commons.utils.isValidEmail
import com.nikhil.mybestfriend.feature.commons.utils.isValidPassword
import com.nikhil.mybestfriend.feature.commons.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class LoginFragment : BaseFragment() {

    private val factory: LoginViewModelFactory by instance()
    private lateinit var viewmodel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun initFragment() {
        initData()
        initButtons()
    }

    private fun initData() {
        viewmodel = ViewModelProviders.of(this, factory)
            .get(LoginViewModel::class.java)
        Glide.with(this)
            .load(R.raw.loading_cat)
            .into(loadingCatView);
        bindUI()
        bindUI()
    }

    private fun bindUI() = launch {
        viewmodel.status.observe(this@LoginFragment, Observer { data ->
            data?.let { status ->
                when (status) {
                    RepoStatus.COMPLETED -> {
                        loadingCatView.visibility = View.GONE
                        gotoHome()
                    }
                    RepoStatus.LOADING -> {
                        loadingCatView.visibility = View.VISIBLE
                    }
                    RepoStatus.ERROR -> {
                        loadingCatView.visibility = View.GONE
                        context?.let {
                            showSnackBar(it.getString(R.string.login_invalid))
                        }

                    }
                    RepoStatus.DATABASE_EXCEPTION -> {
                        loadingCatView.visibility = View.GONE
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
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show();
        }

    }

    private fun initButtons() {
        registerButton.setOnClickListener {
            gotoRegister()
        }

        loginbutton.setOnClickListener {
            var valid = passwordTextInputLayout.isValidPassword()
            valid = emailTextInputLayout.isValidEmail() && valid
            if (valid) {
                login()
            }
        }

    }

    private fun login() = launch {
        viewmodel.login(
            UserEntity(
                emailEditText.data,
                passwordEditText.data
            )
        )
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
