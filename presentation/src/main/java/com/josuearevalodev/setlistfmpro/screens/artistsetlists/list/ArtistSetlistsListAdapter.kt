package com.josuearevalodev.setlistfmpro.screens.artistsetlists.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.base_android.extensions.inflate
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.setlistfmpro.R

class ArtistSetlistsListAdapter() : RecyclerView.Adapter<SetlistListViewHolder>() {

    private val setlists: MutableList<SetlistEntity> = mutableListOf()

    var onSetlistClicked: ((SetlistEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetlistListViewHolder {
        return SetlistListViewHolder(parent.inflate(R.layout.cell_setlist), onSetlistClicked)
    }

    override fun onBindViewHolder(holder: SetlistListViewHolder, position: Int) {
        holder.bind(setlists[position])
    }

    override fun getItemCount(): Int {
        return setlists.size
    }

    fun addSetlists(setlists: List<SetlistEntity>) {
        this.setlists.addAll(setlists)
        notifyDataSetChanged()
    }
}
