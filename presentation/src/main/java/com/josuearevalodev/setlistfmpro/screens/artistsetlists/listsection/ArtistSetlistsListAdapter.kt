package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.base_android.extensions.inflate
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist
import com.josuearevalodev.setlistfmpro.R

class ArtistSetlistsListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEWHOLDER_SETLIST_TYPE = 0
    private val VIEWHOLDER_LOADING_TYPE = 1

    private var isLoadingVisible: Boolean = false

    private val setlists: MutableList<Setlist> = mutableListOf()

    var onSetlistClicked: ((Setlist) -> Unit)? = null

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
            true -> { if (position == setlists.size - 1) VIEWHOLDER_LOADING_TYPE else VIEWHOLDER_SETLIST_TYPE}
            false -> VIEWHOLDER_SETLIST_TYPE
        }
    }

    fun addSetlists(setlists: List<Setlist>) {
        this.setlists.addAll(setlists)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoadingVisible = true;
        setlists.add(Setlist());
        notifyItemInserted(setlists.size - 1);
    }

    fun removeLoading() {
        isLoadingVisible = false;
        val position = setlists.size - 1;
        if (position > -1) {
            setlists[position]?.let { loading ->
                setlists.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

}
