package com.example.traderphd


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.media_controller_view.view.*
import kotlinx.android.synthetic.main.video_view_fragment.*
import java.util.concurrent.TimeUnit


class VideoFragment:Fragment() {
    var isPlaying=false
    var exoplayerView: SimpleExoPlayerView? = null
    var exoPlayer: SimpleExoPlayer? = null
    var mediaSource: MediaSource?=null
    var durationTime="00:00"
    var url = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
//    https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3
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

        playerView.imagePlay.setOnClickListener {
            Log.v("dhjsfgk",exoPlayer?.currentPosition.toString())


            setTotalTimeDuration(exoPlayer?.duration)
            playerView.seekBar.max=exoPlayer?.duration?.toInt()!!
            playerView.textView2.text=durationTime
            playerView.seekBar.progress= exoPlayer?.currentPosition?.toInt()!!

           if (isPlaying){
               playerView.imagePlay.setImageResource(R.drawable.ic_play)

               pause()
               isPlaying=false
           }else{
               playerView.imagePlay.setImageResource(R.drawable.ic_pause)

               playerStart()

               isPlaying  =true
           }

        }
    }

    private fun setTotalTimeDuration(milliSeconds:Long?){

        if (milliSeconds !=null) {
            if (TimeUnit.MILLISECONDS.toHours(milliSeconds)==0L){
                durationTime=  String.format(
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(milliSeconds) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(milliSeconds) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds))
                )}else{
                durationTime=  String.format(
                    "%2d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(milliSeconds),
                    TimeUnit.MILLISECONDS.toMinutes(milliSeconds) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(milliSeconds) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds))
                )
            }
        }

    }
//

    fun playerStart(){
        exoPlayer?.playWhenReady=true

//        playerView.hideController()
    }
    // video = (VideoView) findViewById(R.id.VideoView);
//        String path1 = "http://www.w3schools.com/html5/movie.mp4";
//        MediaController mc = new MediaController(this);
//        mc.setAnchorView(video);
//        mc.setMediaPlayer(video);
//        Uri uri = Uri.parse(path1);
//        video.setMediaController(mc);
//        video.setVideoURI(uri);
//        Button buttonStart = (Button) findViewById(R.id.buttonStart);
//        buttonStart.setOnClickListener(new OnClickListener() {
//
//            public void onClick(View v) {
//                video.start();
//            }
//
//        });
    fun play() {

    }

    fun pause() {
        exoPlayer?.playWhenReady=false
//        playerView.hideController()
    }

}


