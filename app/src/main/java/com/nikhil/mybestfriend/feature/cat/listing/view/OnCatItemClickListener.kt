package com.nikhil.mybestfriend.feature.cat.listing.view

import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity

interface OnCatItemClickListener {
    fun onCatItemClicked(data: UnitCatEntity)
}