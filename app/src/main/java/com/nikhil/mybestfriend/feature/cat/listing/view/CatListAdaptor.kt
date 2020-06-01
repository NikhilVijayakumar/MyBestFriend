package com.nikhil.mybestfriend.feature.cat.listing.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity
import kotlinx.android.synthetic.main.item_cat.view.*


class CatListAdaptor(val catList: MutableList<UnitCatEntity>,
                     val itemClickListener: OnCatItemClickListener) :
    RecyclerView.Adapter<CatListAdaptor.CatViewHolder>() {



    fun updateList(list: List<UnitCatEntity>) {
        catList.clear();
        catList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = catList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.view.catName.text = catList.get(position).name
        holder.view.catLifeSpan.text = catList.get(position).lifeSpan
        loadImage(holder, catList.get(position).url)
        holder.view.setOnClickListener {
           itemClickListener.onCatItemClicked(catList.get(position))
        }
    }


    private fun loadImage(holder: CatViewHolder,url:String?) {
        if(url == null){
            Glide.with(holder.view.context)
                .load(R.drawable.placeholder)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.view.catImageView);
        }else{
            Glide.with(holder.view.context)
                .load(url) // image url
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.placeholder) // any placeholder to load at start
                .error(R.drawable.error)  // any image in case of error
                .into(holder.view.catImageView);
        }



    }

    class CatViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}