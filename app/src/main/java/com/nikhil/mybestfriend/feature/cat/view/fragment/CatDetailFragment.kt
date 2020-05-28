package com.nikhil.mybestfriend.feature.cat.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.api.interceptor.ConnectivityInterceptorImpl
import com.nikhil.mybestfriend.feature.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.base.view.BaseFragment
import com.nikhil.mybestfriend.feature.cat.repo.CatDetailDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.viewmodel.CatDetailViewModel
import kotlinx.android.synthetic.main.fragment_cat_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CatDetailFragment : BaseFragment() {

    private lateinit var viewmodel : CatDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat_detail, container, false)
    }

    override fun initFragment() {
        viewmodel = ViewModelProviders.of(this).get(CatDetailViewModel::class.java)
        observeViewModel()
        callAPI()
    }

    /*Todo remove from here add to view model This is done for testing  api call */
    private fun callAPI() {
        context?.let {
            val apiService = CatAPIService(ConnectivityInterceptorImpl(it))
            val dataSource = CatDetailDataSourceImpl(apiService = apiService)
            dataSource.catDetailList.observe(this, Observer {
                val catDetails =it.get(0)
                val breed = it.get(0).breeds.get(0)
                catName.text = breed.name
                catLifeSpan.text = breed.lifeSpan
                catOrigin.text = breed.origin
                catDescription.text = breed.description
                loadImage(catDetails.url)
            })

            return@let GlobalScope.launch(Dispatchers.Main) {
             dataSource.fetchCatBreed(breedIds="abys")
            }
        }


    }

    private fun loadImage(url:String) {
        Glide.with(this)
            .load(url) // image url
            .placeholder(R.drawable.placeholder) // any placeholder to load at start
            .error(R.drawable.error)  // any image in case of error
            .fitCenter()
           .into(catImageView);

    }

    private fun observeViewModel() {

        viewmodel.catDetails.observe(this, Observer { data ->
            data?.let {
                catName.text = it.breeds.get(0).name
                catLifeSpan.text = it.breeds.get(0).lifeSpan
                catOrigin.text = it.breeds.get(0).origin
                catDescription.text = it.breeds.get(0).description
            }
        })
    }


}

