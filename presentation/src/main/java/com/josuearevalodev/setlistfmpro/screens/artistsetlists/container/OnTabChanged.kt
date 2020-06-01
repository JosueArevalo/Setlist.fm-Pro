package com.josuearevalodev.setlistfmpro.screens.artistsetlists.container

import androidx.viewpager.widget.ViewPager

interface OnTabChanged : ViewPager.OnPageChangeListener{

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}
