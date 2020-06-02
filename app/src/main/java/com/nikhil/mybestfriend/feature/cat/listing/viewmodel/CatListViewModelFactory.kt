package com.nikhil.mybestfriend.feature.cat.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo
import com.nikhil.mybestfriend.feature.preferences.data.PreferenceHelper

class CatListViewModelFactory(
    private val catListRepo: CatListRepo,
    private val preferenceHelper: PreferenceHelper
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatListViewModel(catListRepo,preferenceHelper) as T
    }
}