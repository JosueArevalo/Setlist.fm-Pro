package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.base_android.extensions.inflate
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import com.josuearevalodev.setlistfmpro.R

class ArtistSetlistsListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEWHOLDER_SETLIST_TYPE = 0
    private val VIEWHOLDER_LOADING_TYPE = 1

    private var isLoadingVisible: Boolean = false

    private val setlists: MutableList<SetlistEntity> = mutableListOf()

    var onSetlistClicked: ((SetlistEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEWHOLDER_SETLIST_TYPE -> SetlistListViewHolder(parent.inflate(R.layout.cell_setlist), onSetlistClicked)
            else -> LoadingViewHolder(parent.inflate(R.layout.cell_loading))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SetlistListViewHolder -> holder.bind(setlists[position])
        }
    }

    override fun getItemCount(): Int {
        return setlists.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (isLoadingVisible) {
            true -> VIEWHOLDER_LOADING_TYPE
            false -> VIEWHOLDER_SETLIST_TYPE
        }
    }

    fun addSetlists(setlists: List<SetlistEntity>) {
        this.setlists.addAll(setlists)
        notifyDataSetChanged()
    }
}
