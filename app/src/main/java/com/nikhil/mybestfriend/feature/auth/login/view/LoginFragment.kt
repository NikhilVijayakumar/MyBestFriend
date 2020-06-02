package com.nikhil.mybestfriend.feature.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.auth.login.viewmodel.LoginViewModel
import com.nikhil.mybestfriend.feature.auth.login.viewmodel.LoginViewModelFactory
import com.nikhil.mybestfriend.feature.cat.listing.view.CatListActivity
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.utils.data
import com.nikhil.mybestfriend.feature.commons.utils.isValidEmail
import com.nikhil.mybestfriend.feature.commons.utils.isValidPassword
import com.nikhil.mybestfriend.feature.commons.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class LoginFragment : BaseFragment() {

    private val factory: LoginViewModelFactory by instance<LoginViewModelFactory>()
    private lateinit var viewmodel: LoginViewModel
    private var userList:List<UserEntity>? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        bindUI()
        bindUI()
    }

    private fun bindUI() = launch {
        viewmodel.status.observe(this@LoginFragment, Observer { data ->
            data?.let { status ->
                when (status) {
                    RepoStatus.COMPLETED -> {
                        gotoHome()
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
        viewmodel.data.observe(this@LoginFragment,Observer{
            it?.let {
                userList = it
            }
        })
    }

    private fun showSnackBar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
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
            ),userList
        )
    }



    private fun gotoHome() {
        val intent = Intent(context, CatListActivity::class.java)
        startActivity(intent)
       activity?.finish()
    }

    private fun gotoRegister() {
        Navigation.findNavController(registerButton).navigate(LoginFragmentDirections.actionLoginFragment4ToRegisterFragment())
    }
}
