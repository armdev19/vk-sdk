package com.infernal93.vksdk

import android.app.Application
import com.vk.sdk.VKSdk

/**
 * Created by Armen Mkhitaryan on 09.10.2019.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        VKSdk.initialize(applicationContext)
    }
}