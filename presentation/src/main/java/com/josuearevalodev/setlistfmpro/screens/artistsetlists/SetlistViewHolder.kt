package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.domain.entities.SetlistEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cell_setlist.view.*

class SetlistViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(setlistEntity: SetlistEntity) {
        itemView.tvSetlistId.text = "${setlistEntity.id} (${setlistEntity.eventDate})"
    }
}
