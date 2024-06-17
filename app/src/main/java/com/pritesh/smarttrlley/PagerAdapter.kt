package com.pritesh.smarttrlley

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager?, var tabcount: Int) :
    FragmentPagerAdapter(fm!!, tabcount) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BlankFragment1()
            1 -> BlankFragment2()
            else -> BlankFragment3()
        }
    }

    override fun getCount(): Int {
        return tabcount
    }
}