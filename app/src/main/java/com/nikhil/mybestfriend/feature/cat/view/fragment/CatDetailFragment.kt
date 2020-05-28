package com.nikhil.mybestfriend.feature.cat.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.base.view.BaseFragment


class CatDetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat_detail, container, false)
    }

    override fun initFragment() {

    }


}

