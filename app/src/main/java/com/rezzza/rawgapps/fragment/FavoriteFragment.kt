package com.rezzza.rawgapps.fragment

import android.os.Bundle
import android.view.View
import com.rezzza.rawgapps.R

class FavoriteFragment : MyFragment() {

    companion object{
        fun newInstance(): FavoriteFragment{
            val args = Bundle()

            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_favorite
    }

    override fun initLayout(view: View?) {

    }

    override fun initListener() {

    }
}