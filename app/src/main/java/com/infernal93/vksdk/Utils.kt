package com.infernal93.vksdk

import android.content.Context
import android.widget.Toast

class Utils {

    fun Context.shortToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    fun Context.longToast(text: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, text, duration).show()
    }
}