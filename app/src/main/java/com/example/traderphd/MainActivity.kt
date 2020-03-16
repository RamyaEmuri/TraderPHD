package com.example.traderphd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), LoadInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(VideoFragment())
    }

    override fun loadFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        val fragmentTransaction = ft.replace(R.id.frameLayoutVideoView, fragment)
        fragmentTransaction.addToBackStack(fragment.javaClass.name).commit()

    }

}
