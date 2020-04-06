package com.example.traderphd.advancedPlayer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traderphd.LoadInterface
import com.example.traderphd.R
import com.example.traderphd.VideoFragment
import kotlinx.android.synthetic.main.advance_video_player.*
import kotlinx.android.synthetic.main.video_view_fragment.*

/**
 * Created by Ramya on 31 March,2020
 * Copyrights (c) 2020.
 */
class AdvancedVideoPlayer : AppCompatActivity(){

    private var playerModel: ArrayList<PlayerModel> = ArrayList()
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advance_video_player)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){

        playerModel.add(PlayerModel(R.drawable.bg,"https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://www.radiantmediaplayer.com/media/bbb-360p.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://www.radiantmediaplayer.com/media/bbb-360p.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://www.radiantmediaplayer.com/media/bbb-360p.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"))
        playerModel.add(PlayerModel(R.drawable.bg,"https://www.radiantmediaplayer.com/media/bbb-360p.mp4"))

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(playerModel)
        recyclerView.adapter = adapter

        adapter?.setClickVideoListener(object :Adapter.SendVideoUrl{
            override fun sendVideoUrl(adapterPosition: Int, heading: String?) {
                val intent = Intent(this@AdvancedVideoPlayer,SecondActivity::class.java)
                intent.putExtra(SEND_VIDEO_URL,heading)
                startActivity(intent)
                Toast.makeText(this@AdvancedVideoPlayer, "Clicked on $adapterPosition", Toast.LENGTH_SHORT).show()
            }
        })
    }
    companion object{
        private var SEND_VIDEO_URL="send"
    }
}
