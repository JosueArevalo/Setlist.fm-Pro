package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.list.ArtistSetlistsListFragment
import com.josuearevalodev.setlistfmpro.screens.artistsetlists.map.ArtistSetlistsMapFragment

class ArtistSetlistsPagerAdapter(
    fm: FragmentManager,
    private val titles: List<String>,
    private val artistName: String) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int  = 2

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> { ArtistSetlistsListFragment.createInstance(artistName) }
            else -> { ArtistSetlistsMapFragment() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
