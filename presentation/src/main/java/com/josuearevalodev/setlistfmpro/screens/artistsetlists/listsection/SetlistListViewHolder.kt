package com.josuearevalodev.setlistfmpro.screens.artistsetlists.listsection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.josuearevalodev.domain.setlistfm.entities.SetlistEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cell_setlist.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SetlistListViewHolder(override val containerView: View, private val onSetlistClicked: ((SetlistEntity) -> Unit)? = null) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(setlistEntity: SetlistEntity) {
        itemView.tvVenueTitle.text = "${setlistEntity.venue.name} , ${setlistEntity.venue.city.name} , ${setlistEntity.venue.city.country.name}"
        itemView.tvSongsSummary.text = setlistEntity.getSongSummary

        val splittedDate = setlistEntity.eventDate.getSplittedDate("dd-MM-yyyy")
        itemView.tvMonth.text = splittedDate.first
        itemView.tvDay.text = splittedDate.second
        itemView.tvYear.text = splittedDate.third

        itemView.setOnClickListener {
            onSetlistClicked?.invoke(setlistEntity)
        }
    }

    private fun String.getSplittedDate(dateFormat: String): Triple<String, String, String> {

        val format = SimpleDateFormat(dateFormat)
        try {
            val date: Date = format.parse(this)
            System.out.println(date)

            val newDateFormat = SimpleDateFormat("dd-MMM-yyyy")
            val newFormat = newDateFormat.format(date)
            val elements = newFormat.split("-")
            return Triple(elements[1], elements[0], elements[2])


        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Triple("-", "-", "-")
    }

    private val SetlistEntity.getSongSummary: String
        get() {
            var out = ""
            sets.set.forEach { setlist ->
                setlist.song.forEach { song ->
                    out += "${song.name}, "
                }
            }
            return out
        }


}
