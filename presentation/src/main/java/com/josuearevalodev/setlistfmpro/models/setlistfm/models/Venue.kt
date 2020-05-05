package com.josuearevalodev.domain.setlistfm.entities

data class Venue(val id: String = "",
                 val name: String = "",
                 val city: City = City(),
                 val url: String = "")
