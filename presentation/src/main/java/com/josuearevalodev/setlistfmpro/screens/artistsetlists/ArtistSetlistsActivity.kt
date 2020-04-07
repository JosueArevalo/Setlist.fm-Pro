package com.josuearevalodev.setlistfmpro.screens.artistsetlists

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.josuearevalodev.setlistfmpro.R
import com.josuearevalodev.setlistfmpro.screens.setlistdetail.SetlistDetailActivity
import kotlinx.android.synthetic.main.activity_artist_setlists.*


class ArtistSetlistsActivity : AppCompatActivity(R.layout.activity_artist_setlists) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bNavigate.setOnClickListener {
            startActivity(Intent(this, SetlistDetailActivity::class.java))
        }

    }
}
