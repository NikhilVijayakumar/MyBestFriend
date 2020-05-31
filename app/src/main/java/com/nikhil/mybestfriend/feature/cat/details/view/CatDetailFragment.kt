package com.nikhil.mybestfriend.feature.cat.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.cat.data.db.unitlocalized.UnitCatEntity
import com.nikhil.mybestfriend.feature.cat.details.viewmodel.CatDetailViewModel
import com.nikhil.mybestfriend.feature.cat.details.viewmodel.CatDetailViewModelFactory
import com.nikhil.mybestfriend.feature.commons.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_cat_detail.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class CatDetailFragment : BaseFragment() {

    private val factory: CatDetailViewModelFactory by instance()
    private lateinit var viewmodel : CatDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cat_detail, container, false)
    }

    override fun initFragment() {
        viewmodel = ViewModelProviders.of(this, factory)
            .get(CatDetailViewModel::class.java)
        val entity: UnitCatEntity? = getEntity()
        entity?.let {
            viewmodel.addUnitCatEntity(it)
        }

        bindViewModel()
    }

    private fun getEntity(): UnitCatEntity? {
        activity?.let {
            return (it as CatDetailsActivity).unitCatEntity
        }
        return null
    }

    private fun bindViewModel() = launch {
        val unitCatEntity = viewmodel.unitCatEntity.await()
        unitCatEntity?.observe(this@CatDetailFragment, Observer { data ->
            catName.text = data.name
            catLifeSpan.text = data.lifeSpan
            catOrigin.text = data.origin
            catDescription.text = data.description
            catRatingBar.rating = data.rating.toFloat()
            data.url?.let {
                loadImage(it)
            }

        })
    }

    private fun loadImage(url:String) {
                Glide.with(this)
                 .load(url) // image ur
                 .placeholder(R.drawable.placeholder)
                 .error(R.drawable.error)  // any image in case of error
                 .fitCenter()
                .into(catImageView);

    }

}

