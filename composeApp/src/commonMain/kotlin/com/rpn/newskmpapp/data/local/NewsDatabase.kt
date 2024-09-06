package com.rpn.newskmpapp.data.local

//import androidx.room.ConstructedBy
//import androidx.room.RoomDatabaseConstructor
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rpn.newskmpapp.data.local.dao.CachedArticleDao
import com.rpn.newskmpapp.data.local.dao.FavoriteArticleDao
import com.rpn.newskmpapp.data.local.dao.NewsDao
import com.rpn.newskmpapp.data.local.entity.ArticleEntity
import com.rpn.newskmpapp.data.local.entity.CachedArticleEntity
import com.rpn.newskmpapp.data.local.entity.FavoriteArticleEntity

@Database(
    entities = [ArticleEntity::class, CachedArticleEntity::class, FavoriteArticleEntity::class],
    version = 4,
    exportSchema = false,
)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(), DB {

    abstract fun newsDao(): NewsDao
    abstract fun cachedArticleDao(): CachedArticleDao
    abstract fun favouriteArticleDao(): FavoriteArticleDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables(): Unit {}
}
