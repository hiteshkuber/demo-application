package com.example.demure_demo_app.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun getCount() = fragmentTitleList.size

    override fun getPageTitle(position: Int) = fragmentTitleList[position]

    override fun getItem(position: Int) = fragmentList[position]

    fun addFragment(fragment: Fragment, title:String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}