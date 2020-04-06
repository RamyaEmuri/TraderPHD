package com.example.traderphd.advancedPlayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traderphd.R
import java.util.ArrayList

/**
 * Created by Ghouse on 31 March,2020
 * Copyrights (c) 2020.
 */
class Adapter(var playerModel: ArrayList<PlayerModel>) :
    RecyclerView.Adapter<Adapter.PlayerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.player_view, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerModel.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(playerModel[position])
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(playerModel: PlayerModel) {
            Glide.with(MyApplication.getApplicationContext()).asBitmap().load(playerModel.image)
                .placeholder(R.drawable.bg).into(imagePoster)

            textHeading.text = playerModel.heading
            itemLayout.setOnClickListener {
                sendVideoUrl?.sendVideoUrl(adapterPosition, playerModel.heading)
            }
        }

        private var imagePoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private var textHeading: TextView = itemView.findViewById(R.id.textViewUrl)
        private var itemLayout: CardView = itemView.findViewById(R.id.cardView)

    }

    private var sendVideoUrl: SendVideoUrl? = null

    fun setClickVideoListener(sendVideoUrl: SendVideoUrl) {
        this.sendVideoUrl = sendVideoUrl
    }

    interface SendVideoUrl {
        fun sendVideoUrl(adapterPosition: Int, heading: String?)
    }
}
