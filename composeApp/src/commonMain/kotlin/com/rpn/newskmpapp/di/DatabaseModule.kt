package com.rpn.newskmpapp.di


import com.rpn.newskmpapp.data.utils.AppPreferences
import com.rpn.newskmpapp.utils.dataStorePreferences
import org.koin.dsl.module

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.rpn.newskmpapp.data.local.NewsDatabase
import com.rpn.newskmpapp.utils.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

val databaseModule = module {

    // database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }
    // datastore
    single {
        AppPreferences(dataStorePreferences())
    }

}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}