package com.app.jokeapp.util

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun currentDate(time: Long): String {
        val formatter = SimpleDateFormat("EEEE, dd MMM y,hh:mm a", Locale.getDefault())
        return formatter.format(time)
    }
}