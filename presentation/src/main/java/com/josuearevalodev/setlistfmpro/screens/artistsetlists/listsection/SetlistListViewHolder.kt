package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cell_setlist.view.*

class SetlistListViewHolder(override val containerView: View, private val onSetlistClicked: ((SetlistEntity) -> Unit)? = null) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(setlistEntity: SetlistEntity) {
        itemView.tvVenueTitle.text = "${setlistEntity.venue.name} , ${setlistEntity.venue.city.name} , ${setlistEntity.venue.city.country.name}"
        itemView.tvVenueDate.text = setlistEntity.eventDate

        itemView.setOnClickListener {
            onSetlistClicked?.invoke(setlistEntity)
        }
    }
}
