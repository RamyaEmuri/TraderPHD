package com.example.traderphd


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.media_controller_view.*
import kotlinx.android.synthetic.main.media_controller_view.view.*
import kotlinx.android.synthetic.main.video_view_fragment.*
import java.util.concurrent.TimeUnit


class VideoFragment:Fragment() {
    var exoPlayer: SimpleExoPlayer? = null
    var mediaSource: MediaSource?=null
    var url = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
//    https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3 is for audio
//    https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4 is for video
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.video_view_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context)
        val videoUrl :Uri = Uri.parse(url)
        val dataSourceFactory = DefaultDataSourceFactory(context,"exoplayer_audio")
        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
        mediaSource = ExtractorMediaSource(videoUrl, dataSourceFactory, extractorsFactory, null, null)
        playerView.player = exoPlayer
        exoPlayer?.prepare(mediaSource)
        exoPlayer?.playWhenReady=false
    }
}

