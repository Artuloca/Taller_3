package com.example.taller_3

import android.content.AsyncTaskLoader
import android.content.Context
import java.util.*

class GreetingAsyncTaskLoader(context: Context) : androidx.loader.content.AsyncTaskLoader<String>(context) {
    override fun loadInBackground(): String? {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 0..11 -> "Buenos días"
            in 12..17 -> "Buenas tardes"
            else -> "Buenas noches"
        }
    }

    override fun onStartLoading() {
        forceLoad()
    }
}