package com.mtnine.amuidea.util

import java.text.SimpleDateFormat
import java.util.Locale

object Util {
    fun getDateFormat() : String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(System.currentTimeMillis())
    }
}