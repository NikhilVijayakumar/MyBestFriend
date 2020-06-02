package com.nikhil.mybestfriend.feature.cat.listing.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikhil.mybestfriend.R
import com.nikhil.mybestfriend.databinding.ItemCatBinding
import com.nikhil.mybestfriend.feature.cat.data.db.localized.UnitCatEntity
import kotlinx.android.synthetic.main.item_cat.view.*


class CatListAdaptor(val catList: MutableList<UnitCatEntity>,
                     val itemClickListener: OnCatItemClickListener) :
    RecyclerView.Adapter<CatListAdaptor.CatViewHolder>() {
    fun updateList(list: List<UnitCatEntity>) {
        catList.clear()
        catList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {

        val view = DataBindingUtil.inflate<ItemCatBinding>( LayoutInflater.from(parent.context),R.layout.item_cat,parent,false)
        return CatViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = catList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val data = catList[position]
        val onclickListner = object : View.OnClickListener{
            override fun onClick(v: View?) {
                itemClickListener.onCatItemClicked(data)
            }
        }
        holder.view.entity = data
        holder.view.listner = onclickListner
    }

    class CatViewHolder(var view: ItemCatBinding) : RecyclerView.ViewHolder(view.root)
}