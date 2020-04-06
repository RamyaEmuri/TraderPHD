package com.example.traderphd.advancedPlayer

import android.app.Application
import android.content.Context

/**
 * Created by Ghouse on 01 April,2020
 * Copyrights (c) 2020.
 */
class MyApplication:Application() {
    init {
        myApplication = this
    }
    companion object {
        private lateinit var myApplication: Application
        fun getApplicationContext(): Context {
            return myApplication
        }
    }
}