package com.nikhil.mybestfriend.feature.cat.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.mybestfriend.feature.cat.data.repo.CatDetailRepo
import com.nikhil.mybestfriend.feature.preferences.data.PreferenceHelper

class CatDetailViewModelFactory(
    private val catDetailRepo: CatDetailRepo,
    private val preferenceHelper: PreferenceHelper
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatDetailViewModel(catDetailRepo,preferenceHelper) as T
    }
}