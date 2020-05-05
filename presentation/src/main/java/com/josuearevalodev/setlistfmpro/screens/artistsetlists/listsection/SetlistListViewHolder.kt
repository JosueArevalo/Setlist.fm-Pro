package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.domain.setlistfm.entities.Setlist
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cell_setlist.view.*


class SetlistListViewHolder(override val containerView: View, private val onSetlistClicked: ((Setlist) -> Unit)? = null) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(setlist: Setlist) {
        with (itemView.tvVenueTitle) {
            text = "${setlist.venue.name} , ${setlist.venue.city.name} , ${setlist.venue.city.country.name}"
            paintFlags = (paintFlags or Paint.UNDERLINE_TEXT_FLAG)
        }

        itemView.tvSongsSummary.text = setlist.songSummary

        itemView.tvMonth.text = setlist.month
        itemView.tvDay.text = setlist.day
        itemView.tvYear.text = setlist.year

        itemView.setOnClickListener {
            onSetlistClicked?.invoke(setlist)
        }
    }




}
