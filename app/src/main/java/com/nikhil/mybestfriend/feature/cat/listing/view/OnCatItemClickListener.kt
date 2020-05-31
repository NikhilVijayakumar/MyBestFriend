package com.nikhil.mybestfriend.feature.cat.listing.view

import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity

interface OnCatItemClickListener {
    fun onCatItemClicked(data: UnitCatEntity)
}