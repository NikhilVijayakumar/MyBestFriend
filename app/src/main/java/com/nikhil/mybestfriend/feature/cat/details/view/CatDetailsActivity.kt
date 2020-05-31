package com.nikhil.mybestfriend.feature.cat.details.view

import android.os.Bundle
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity
import com.nikhil.mybestfriend.feature.cat.listing.view.CatListFragment
import com.nikhil.mybestfriend.feature.commons.view.BaseActivity


class CatDetailsActivity : BaseActivity() {
     var unitCatEntity:UnitCatEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_details)
        initData()
    }

    private fun initData() {
        val i = intent
        val bundle = i.getBundleExtra(CatListFragment.KEY)
        bundle?.let {
            unitCatEntity = it.get(CatListFragment.KEY) as UnitCatEntity
        }


    }
}