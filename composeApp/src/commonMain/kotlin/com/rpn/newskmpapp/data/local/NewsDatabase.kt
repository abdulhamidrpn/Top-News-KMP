package com.rpn.newskmpapp.data.local

//import androidx.room.ConstructedBy
//import androidx.room.RoomDatabaseConstructor
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rpn.newskmpapp.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
//@ConstructedBy(AppDatabaseConstructor::class)
abstract class NewsDatabase : RoomDatabase(), DB {
    abstract fun newsDao(): NewsDao
    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables(): Unit {}
}

// The Room compiler generates the `actual` implementations.
//@Suppress("NO_ACTUAL_FOR_EXPECT")
//expect object AppDatabaseConstructor : RoomDatabaseConstructor<NewsDatabase>
