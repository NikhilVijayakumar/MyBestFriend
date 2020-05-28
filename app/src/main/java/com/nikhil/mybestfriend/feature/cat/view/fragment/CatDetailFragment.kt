package com.nikhil.mybestfriend.feature.cat.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.api.CatAPIService
import com.nikhil.mybestfriend.feature.base.view.BaseFragment
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

        val apiService = CatAPIService()
        GlobalScope.launch(Dispatchers.Main) {
            val data = apiService.getCatDetails(breedIds="abys");
            val catDetails = data.await().get(0)
            catName.text = catDetails.breeds.get(0).name
            catLifeSpan.text = catDetails.breeds.get(0).lifeSpan
            catOrigin.text = catDetails.breeds.get(0).origin
            catDescription.text = catDetails.breeds.get(0).description
            loadImage(catDetails.url)
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

