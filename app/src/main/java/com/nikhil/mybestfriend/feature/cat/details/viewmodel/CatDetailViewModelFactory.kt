package com.nikhil.mybestfriend.feature.cat.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.mybestfriend.feature.cat.data.repo.CatDetailRepo
import com.nikhil.mybestfriend.feature.cat.data.repo.CatListRepo
import com.nikhil.mybestfriend.feature.cat.listing.viewmodel.CatListViewModel

class CatDetailViewModelFactory(
    val catDetailRepo: CatDetailRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatDetailViewModel(catDetailRepo) as T
    }
}