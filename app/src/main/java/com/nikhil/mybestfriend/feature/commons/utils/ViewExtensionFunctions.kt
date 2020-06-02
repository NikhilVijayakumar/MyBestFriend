package com.nikhil.mybestfriend.feature.commons.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.nikhil.mybestfriend.R

val TextInputEditText.data: String
    get() = this.text.toString().trim()


fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.length >= 6
}

fun String.isEmpty(): Boolean {
    return this.trim().length == 0
}

fun TextInputLayout.isValidPassword(): Boolean {
    this.error = null
    this.editText?.let {
        val password = it.text.toString()
        if (password.isEmpty()) {
            this.error = context.getString(R.string.password_empty)
            return false
        }
        if (!password.isValidPassword()) {
            this.error = context.getString(R.string.password_error_length)
            return false
        }
        return true
    }
    return false
}

fun TextInputLayout.isValidEmail(): Boolean {
    this.error = null
    this.editText?.let {
        val email = it.text.toString()
        if (email.isEmpty()) {
            this.error = context.getString(R.string.email_empty)
            return false
        }
        if (!email.isValidEmail()) {
            this.error = context.getString(R.string.email_error_fromat)
            return false
        }
        return true
    }
    return false
}

fun ImageView.load(url:String?){
    if(url == null){
        Glide.with(context)
            .load(R.drawable.placeholder)
            .apply(RequestOptions.circleCropTransform())
            .into(this);
    }else{
        Glide.with(context)
            .load(url) // image url
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.placeholder) // any placeholder to load at start
            .error(R.drawable.error)  // any image in case of error
            .into(this);
    }
}

@BindingAdapter("android:imageUrl")
fun loadImage(view:ImageView,url:String?){
    view.load(url)
}




