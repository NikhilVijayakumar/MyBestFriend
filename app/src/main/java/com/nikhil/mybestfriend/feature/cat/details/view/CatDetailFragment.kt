package com.nikhil.mybestfriend.feature.cat.details.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.databinding.FragmentCatDetailBinding
import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity
import com.nikhil.mybestfriend.feature.cat.data.view.CatPalette
import com.nikhil.mybestfriend.feature.cat.details.viewmodel.CatDetailViewModel
import com.nikhil.mybestfriend.feature.cat.details.viewmodel.CatDetailViewModelFactory
import com.nikhil.mybestfriend.feature.commons.view.BaseFragment
import com.nikhil.mybestfriend.feature.preferences.view.SettingsActivity
import kotlinx.android.synthetic.main.fragment_cat_detail.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class CatDetailFragment : BaseFragment() {

    private val factory: CatDetailViewModelFactory by instance<CatDetailViewModelFactory>()
    private lateinit var viewmodel : CatDetailViewModel
    private lateinit var dataBinding: FragmentCatDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_cat_detail,container,false)
        return dataBinding.root
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
          dataBinding.entity = data
            data.url?.let(this@CatDetailFragment::loadImage)
        })
    }

    private fun loadImage(url:String) {
                Glide.with(this)
                 .load(url) // image ur
                 .placeholder(R.drawable.placeholder)
                 .error(R.drawable.error)  // any image in case of error
                 .fitCenter()
                .into(catImageView);
        setPalette(url)

    }

    private fun setPalette(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {

                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            context?.let {
                                val backgroundColor = palette?.vibrantSwatch?.rgb?: ContextCompat.getColor(it, R.color.colorPrimary)
                                val textColor = palette?.vibrantSwatch?.bodyTextColor?: ContextCompat.getColor(it, R.color.defaultTextColor)
                                val titleColor = palette?.vibrantSwatch?.titleTextColor?: ContextCompat.getColor(it, R.color.defaultTitleColor)
                                val catPalette = CatPalette(backgroundColor,titleColor,textColor)
                                dataBinding.palette = catPalette
                                setRatingBarColor(textColor)
                                toolbarColor(backgroundColor)
                            }

                        }
                }
            })
    }

    private fun toolbarColor(backgroundColor: Int) {

        activity?.let {
            it.window.setStatusBarColor(backgroundColor);
        }

    }

    private fun setRatingBarColor(textColor: Int) {
        val stars = catRatingBar.progressDrawable as LayerDrawable
        stars.getDrawable(2).setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(textColor, BlendModeCompat.SRC_ATOP))
    }



}

