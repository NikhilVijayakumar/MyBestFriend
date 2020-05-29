package com.nikhil.mybestfriend.feature.cat.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo

class CatListViewModelFactory(
    val catListRepo: CatListRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatListViewModel(catListRepo) as T
    }
}