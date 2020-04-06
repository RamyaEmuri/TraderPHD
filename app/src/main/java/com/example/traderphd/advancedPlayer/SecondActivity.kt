package com.example.traderphd.advancedPlayer

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.traderphd.R
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.exo_playback_control_view.*


/**
 * Created by Ghouse on 02 April,2020
 * Copyrights (c) 2020.
 */
class SecondActivity: AppCompatActivity() {

    private var exoPlayer: SimpleExoPlayer? = null
    private var mediaSource: MediaSource?=null
    private var videoUrl : Uri?=null

    var fullscreen = false

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_second)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(this)
        if (intent != null){
            val intentVideoUrl = intent.getStringExtra("send")
            videoUrl = Uri.parse(intentVideoUrl)
        }
//        val videoUrl : Uri = Uri.parse(url)
        val dataSourceFactory = DefaultDataSourceFactory(this,"exoplayer_audio")
        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
        mediaSource = ExtractorMediaSource(videoUrl, dataSourceFactory, extractorsFactory, null, null)
        videoPlayer.player = exoPlayer
//        videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        exoPlayer?.prepare(mediaSource)
        exoPlayer?.playWhenReady=false


        videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL)
        exoPlayer?.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
//        videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)


        exo_fullscreen_icon.setOnClickListener(View.OnClickListener {
            if (fullscreen) {
                exo_fullscreen_icon.setImageDrawable(ContextCompat.getDrawable(this@SecondActivity,R.drawable.ic_fullscreen))
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                if (supportActionBar != null) {
                    supportActionBar!!.show()
                }
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                val params =
                    videoPlayer.getLayoutParams() as ConstraintLayout.LayoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = (200 * applicationContext.resources
                    .displayMetrics.density).toInt()
                videoPlayer.setLayoutParams(params)
                fullscreen = false
            } else {
                exo_fullscreen_icon.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@SecondActivity,
                        R.drawable.ic_exit_fullscreen
                    )
                )
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                if (supportActionBar != null) {
                    supportActionBar!!.hide()
                }
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                val params =
                    videoPlayer.getLayoutParams() as ConstraintLayout.LayoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = ViewGroup.LayoutParams.MATCH_PARENT
                videoPlayer.setLayoutParams(params)
                fullscreen = true
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        releaseExoPlayer(exoPlayer)
    }

    fun releaseExoPlayer(exoPlayer: SimpleExoPlayer?) {
        exoPlayer?.release()
    }
}
































//        exoPlayer = ExoPlayerFactory.newSimpleInstance(this)
//        if (intent != null){
//            val intentVideoUrl = intent.getStringExtra("send")
//             videoUrl = Uri.parse(intentVideoUrl)
//        }
////        val videoUrl : Uri = Uri.parse(url)
//        val dataSourceFactory = DefaultDataSourceFactory(this,"exoplayer_audio")
//        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
//        mediaSource = ExtractorMediaSource(videoUrl, dataSourceFactory, extractorsFactory, null, null)
//        videoPlayer.player = exoPlayer
////        videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
//        exoPlayer?.prepare(mediaSource)
//        exoPlayer?.playWhenReady=false




//val haveResumePosition = mResumeWindow != C.INDEX_UNSET
//if (haveResumePosition) {
//            exoPlayer.player.seekTo(mResumeWindow, mResumePosition)
//        }