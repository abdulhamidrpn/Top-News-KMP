package com.rpn.newskmpapp.di


import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.rpn.newskmpapp.data.datastore.AppPreferences
import com.rpn.newskmpapp.data.local.NewsDatabase
import com.rpn.newskmpapp.data.local.DatabaseMigrations.migration1to2
import com.rpn.newskmpapp.utils.dataStorePreferences
import com.rpn.newskmpapp.utils.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

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

/*
* add Migration to database for updating room database
* */
fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder

        .fallbackToDestructiveMigration(true)
        .addMigrations(migration1to2)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}