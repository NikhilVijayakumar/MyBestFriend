package com.nikhil.mybestfriend.feature.cat.listing.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity
import com.nikhil.mybestfriend.feature.cat.details.view.CatDetailsActivity
import com.nikhil.mybestfriend.feature.cat.listing.viewmodel.CatListViewModel
import com.nikhil.mybestfriend.feature.cat.listing.viewmodel.CatListViewModelFactory
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_cat_list.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class CatListFragment : BaseFragment(), OnCatItemClickListener {

    private val factory: CatListViewModelFactory by instance<CatListViewModelFactory>()
    private lateinit var viewmodel : CatListViewModel
    private var catListAdaptor =
        CatListAdaptor(
            arrayListOf(), this
        )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cat_list, container, false)
    }


    override fun initFragment() {
        initData()
    }


    private fun initData() {
        viewmodel = ViewModelProviders.of(this, factory)
            .get(CatListViewModel::class.java)
        catRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catListAdaptor
        }
        Glide.with(this)
            .load(R.raw.loading_cat)
            .into(loadingCatView);
        bindUI()
    }

    private fun bindUI() = launch {
        val catList = viewmodel.catList.await()
        catList.observe(this@CatListFragment, Observer { datalist ->
            datalist?.let {
                catRecyclerView.visibility = View.VISIBLE
                catListAdaptor.updateList(it)
            }
        })
        viewmodel.status.observe(this@CatListFragment,Observer{status ->
            when(status){
                RepoStatus.COMPLETED -> {
                    catRecyclerView.visibility = View.VISIBLE
                    catErrorTextView.visibility = View.GONE
                    loadingCatView.visibility = View.GONE
                }
                RepoStatus.LOADING -> {
                    catRecyclerView.visibility = View.GONE
                    catErrorTextView.visibility = View.GONE
                    loadingCatView.visibility = View.VISIBLE
                }
                RepoStatus.ERROR -> {
                    catRecyclerView.visibility = View.GONE
                    catErrorTextView.visibility = View.VISIBLE
                    loadingCatView.visibility = View.GONE
                }
            }

        })
    }

    override fun onCatItemClicked(data: UnitCatEntity) {
        val intent = Intent(context, CatDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(KEY, data)
        intent.putExtra(KEY, bundle)
        startActivity(intent)
    }



    companion object {
        const val KEY: String = "CatListFragment";
    }
}


