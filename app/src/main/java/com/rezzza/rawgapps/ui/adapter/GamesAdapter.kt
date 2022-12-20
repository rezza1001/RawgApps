package com.rezzza.rawgapps.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.balysv.materialripple.MaterialRippleLayout
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.model.GamesModel
import java.text.SimpleDateFormat
import java.util.*

class GamesAdapter(val context: Context?, val list: ArrayList<GamesModel>) : RecyclerView.Adapter<GamesAdapter.AdapterView>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterView {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_game, parent, false)
        return AdapterView(itemView)
    }

    override fun onBindViewHolder(holder: AdapterView, position: Int) {
        val data = list[position]
        if (data.isLoading){
            holder.mrly_body.visibility = View.GONE
            holder.shmr_load.visibility = View.VISIBLE
            holder.shmr_load.startShimmerAnimation()
        }
        else {
            holder.mrly_body.visibility = View.VISIBLE
            holder.shmr_load.visibility = View.GONE
            holder.shmr_load.stopShimmerAnimation()

            holder.txvw_title.text = data.title

            val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val release = "Release date : "+formatDate.format(data.releaseDate)
            holder.txvw_release.text = release
            val rating = data.rating.toString()
            holder.txvw_rating.text = rating

            if (context != null) {
                Glide.with(context).load(data.imageUrl).into(holder.imvw_image)
            }

            holder.mrly_body.setOnClickListener {
                onSelectListener?.onSelect(data)
            }
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }

    class AdapterView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mrly_body = itemView.findViewById<MaterialRippleLayout>(R.id.mrly_body)
        val imvw_image = itemView.findViewById<ImageView>(R.id.imvw_image)
        val txvw_title = itemView.findViewById<TextView>(R.id.txvw_shimmer1)
        val txvw_release = itemView.findViewById<TextView>(R.id.txvw_release)
        val txvw_rating = itemView.findViewById<TextView>(R.id.txvw_rating)
        val shmr_load = itemView.findViewById<ShimmerFrameLayout>(R.id.shmr_load)
    }

    var onSelectListener : OnSelectListener ?= null

    @JvmName("setOnSelectListener1")
    public fun setOnSelectListener(onSelectListener: OnSelectListener){
        this.onSelectListener = onSelectListener
    }
    interface OnSelectListener{
        fun onSelect(data : GamesModel)
    }

}