package com.rezzza.rawgapps.ui.activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.ui.fragment.FavoriteFragment
import com.rezzza.rawgapps.ui.fragment.HomeFragment
import com.rezzza.rawgapps.ui.view.MenuBottomView

class MainActivity : AppCompatActivity(), MenuBottomView.OnActionListener{

    private var mnvw_menu : MenuBottomView ?= null
    private var frame_body : FrameLayout ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mnvw_menu = findViewById(R.id.mnvw_menu)
        frame_body = findViewById(R.id.frame_body)

        initListener()
        onSelectedPage(MenuBottomView.MENU.HOME)
    }

    private fun initListener(){
        mnvw_menu?.setOnActionListener(this)

    }

    override fun OnAction(menu: MenuBottomView.MENU) {
        onSelectedPage(menu)
    }

    private fun onSelectedPage(menu: MenuBottomView.MENU){
        var fragment : Fragment ?= null
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()

        if (menu == MenuBottomView.MENU.HOME){
            fragment = HomeFragment.newInstance()
            fragmentTransaction.replace(frame_body!!.id, fragment, "home")
        }
        else if (menu == MenuBottomView.MENU.FAVORITE){
            fragment = FavoriteFragment.newInstance()
            fragmentTransaction.replace(frame_body!!.id, fragment, "favorite")
        }

        if (fragment == null){
            return
        }

        fragmentTransaction.detach(fragment)
        fragmentTransaction.attach(fragment)
        fragmentTransaction.commit()
    }
}