package com.nikhil.mybestfriend.feature.cat.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.api.interceptor.ConnectivityInterceptorImpl
import com.nikhil.mybestfriend.feature.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.base.view.BaseFragment
import com.nikhil.mybestfriend.feature.cat.repo.CatBreedDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.view.adaptor.CatListAdaptor
import com.nikhil.mybestfriend.feature.cat.viewmodel.CatListViewModel
import kotlinx.android.synthetic.main.fragment_cat_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CatListFragment : BaseFragment() {

    private lateinit var viewmodel : CatListViewModel
    private var catListAdaptor = CatListAdaptor(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat_list, container, false)
    }


    override fun initFragment() {
        initData()
        callAPI()
    }
    /*Todo remove from here add to view model This is done for testing  api call */
    private fun callAPI() {
        context?.let {
            val apiService = CatAPIService(ConnectivityInterceptorImpl(it))
            val dataSource = CatBreedDataSourceImpl(apiService = apiService)
            dataSource.catList.observe(this, Observer {
                catListAdaptor.updateList(it)
            })
            return@let GlobalScope.launch(Dispatchers.Main) {
                dataSource.fetchCatBreed()
            }
        }


    }

    private fun initData() {
        viewmodel = ViewModelProviders.of(this).get(CatListViewModel::class.java)
        catRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catListAdaptor
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewmodel.catList.observe(this, Observer { datalist ->
            datalist?.let {
                catRecyclerView.visibility = View.VISIBLE
                catListAdaptor.updateList(it)
            }
        })

        viewmodel.apiError.observe(this, Observer { error ->
            error?.let {
                catErrorTextView.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewmodel.isLoading.observe(this, Observer { loading ->
            loading?.let {
                if(it){
                    catErrorTextView.visibility = View.GONE
                    catRecyclerView.visibility = View.GONE
                    catProgressBar.visibility = View.VISIBLE
                }else{
                    catProgressBar.visibility = View.GONE
                }
            }
        })


    }

}
