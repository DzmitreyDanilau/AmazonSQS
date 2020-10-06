package com.example.test

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.TimelineChangeReason
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.manifest.DashManifest
import com.google.android.exoplayer2.trackselection.TrackSelectionUtil
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RTMP Url
        val url =
            "http://amssamples.streaming.mediaservices.windows.net/91492735-c523-432b-ba01-faba6c2206a2/AzureMediaServicesPromo.ism"

        /*
          Create Simple Exoplayer Player
         */

        /*
          Create Simple Exoplayer Player
         */
//        val dashUri ="https://vhpgomediaservice-usea.streaming.media.azure.net/0657fd56-504d-4953-ab61-43f43c2c1d99/output.ism/manifest"
        val dashUri ="https://vhpgomediaservice-usea.streaming.media.azure.net/a185921d-5d05-4571-8d1a-f9c6bf0cce47/output.ism/manifest(format=mpd-time-csf)"


        // Create a data source factory.
        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSourceFactory(Util.getUserAgent(this, ""))
        // Create a DASH media source pointing to a DASH manifest uri.
        val mediaSource: MediaSource = DashMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(dashUri))
        // Create a player instance.
        val player: SimpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        // Prepare the player with the media source.
        player.prepare(mediaSource)

        player.addListener(
            object : Player.EventListener {
                override fun onTimelineChanged(
                    timeline: Timeline, @TimelineChangeReason reason: Int
                ) {
                    val manifest = player.currentManifest
                    if (manifest != null) {
                        val dashManifest = manifest as DashManifest
                        // Do something with the manifest.
                    }
                }
            })

        player.setPlayWhenReady(true)

        simple_player.player = player
    }
}