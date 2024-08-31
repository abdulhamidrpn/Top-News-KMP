package com.rpn.newskmpapp.utils

import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rpn.newskmpapp.ContextUtils
import com.rpn.newskmpapp.data.local.NewsDatabase
import com.rpn.newskmpapp.data.utils.Constants
import com.rpn.newskmpapp.data.utils.Constants.DB_NAME
import java.util.UUID

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    try {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Link")
        ContextUtils.context.startActivity(shareIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

actual fun dataStorePreferences(): DataStore<Preferences> {
    val context = ContextUtils.context
    return AppSettings.getDataStore(
        producerPath = {
            context.filesDir.resolve(Constants.dataStoreFileName).absolutePath
        }
    )
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val appContext = ContextUtils.context// KoinPlatform.getKoin().get<Application>()
    val dbFile = appContext.getDatabasePath(DB_NAME)
    return Room.databaseBuilder<NewsDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

