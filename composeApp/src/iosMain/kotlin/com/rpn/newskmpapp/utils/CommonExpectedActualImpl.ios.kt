package com.rpn.newskmpapp.utils


import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import data.database.instantiateImpl
import platform.UIKit.*
import platform.Foundation.NSUUID
import platform.Foundation.NSHomeDirectory

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rpn.newskmpapp.data.local.NewsDatabase
import com.rpn.newskmpapp.data.local.instantiateImpl
import com.rpn.newskmpapp.data.utils.Constants
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.UIKit.*

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/${Constants.DB_NAME}"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory =  { NewsDatabase::class.instantiateImpl() }
    )
}


actual fun getRandomId(): String {
    return NSUUIID.UUIDString()
}


actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/${Constants.dataStoreFileName}"
        })
}
