package com.rezzza.rawgapps.ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.rezzza.rawgapps.R

class MenuBottomView(context: Context?,attrs: AttributeSet?) : MyView(context, attrs){

    public enum class MENU {
        HOME, FAVORITE
    }

    private var lnly_home : LinearLayout ?= null
    private var lnly_favorite : LinearLayout ?= null

    private var actionListener : OnActionListener ?= null

    override fun setlayout(): Int {
       return R.layout.view_menu_bottom
    }

    override fun initLayout() {
        lnly_home = findViewById(R.id.lnly_home);
        lnly_favorite = findViewById(R.id.lnly_favorite);
        selected(lnly_home!!)
    }

    override fun initListener() {
        lnly_home?.setOnClickListener {
            actionListener?.OnAction(MENU.HOME)
            selected(lnly_home!!)
            unselected(lnly_favorite!!)
        }
        lnly_favorite?.setOnClickListener {
            actionListener?.OnAction(MENU.FAVORITE)
            selected(lnly_favorite!!)
            unselected(lnly_home!!)
        }
    }

    private fun unselected(lnly: LinearLayout){
        val imvw = lnly.getChildAt(0) as ImageView
        imvw.setColorFilter(ContextCompat.getColor(context, R.color.menu_unselect))

        val txvw = lnly.getChildAt(1) as TextView
        txvw.setTextColor(ContextCompat.getColor(context, R.color.menu_unselect))
        txvw.typeface = Typeface.DEFAULT
    }


    private fun selected(lnly: LinearLayout){
        val imvw = lnly.getChildAt(0) as ImageView
        imvw.setColorFilter(ContextCompat.getColor(context, R.color.primary))

        val txvw = lnly.getChildAt(1) as TextView
        txvw.setTextColor(ContextCompat.getColor(context, R.color.primary))
        txvw.typeface = Typeface.DEFAULT_BOLD
    }

    fun setOnActionListener(actionListener: OnActionListener){
        this.actionListener = actionListener;
    }

    interface OnActionListener {
        fun OnAction(menu : MENU);
    }

}