package com.josuearevalodev.setlistfmpro.models.setlistfm.models

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class Setlist(
    val id: String = "",
    val versionId: String = "",
    val eventDate: String = "",
    val lastUpdated: String = "",
    val artist: Artist = Artist(),
    val venue: Venue = Venue(),
    val tour: Tour = Tour(),
    val sets: Sets = Sets(),
    val url: String = ""
) {

    val eventDateFormat = "dd-MM-yyyy"

    fun getSplittedDate(dateFormat: String): Triple<String, String, String> {

        val format = SimpleDateFormat(dateFormat)
        try {
            val date: Date = format.parse(eventDate)
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

    val day: String
        get() = getDatePart(position = 0)

    val month: String
        get() = getDatePart(position = 1)

    val year: String
        get() = getDatePart(position = 2)

    private fun getDatePart(position: Int): String {
        val format = SimpleDateFormat(eventDateFormat)
        try {
            val date: Date = format.parse(eventDate)
            System.out.println(date)

            val newDateFormat = SimpleDateFormat("dd-MMM-yyyy")
            val newFormat = newDateFormat.format(date)
            val elements = newFormat.split("-")
            return elements[position].toUpperCase()


        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return "-"
    }


    val songSummary: String
        get() {
            var out = ""
            sets.set.forEach { setlist ->
                setlist.song.filter { song -> !song.tape }.forEach { song ->
                    out += "${song.name}, "
                }
            }
            return out
        }
}
