package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.setlistfmpro.databinding.CellSetlistBinding
import com.josuearevalodev.setlistfmpro.models.setlistfm.models.Setlist
import kotlinx.android.extensions.LayoutContainer

class SetlistListViewHolder(
    override val containerView: View,
    private val onSetlistClicked: ((Setlist) -> Unit)? = null
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val binding = CellSetlistBinding.bind(containerView)

    fun bind(setlist: Setlist) {
        with (binding.tvVenueTitle) {
            text = "${setlist.venue.name} , ${setlist.venue.city.name} , ${setlist.venue.city.country.name}"
            paintFlags = (paintFlags or Paint.UNDERLINE_TEXT_FLAG)
        }

        binding.tvSongsSummary.text = setlist.songSummary

        binding.llDate.tvMonth.text = setlist.month
        binding.llDate.tvDay.text = setlist.day
        binding.llDate.tvYear.text = setlist.year

        itemView.setOnClickListener {
            onSetlistClicked?.invoke(setlist)
        }
    }
}
