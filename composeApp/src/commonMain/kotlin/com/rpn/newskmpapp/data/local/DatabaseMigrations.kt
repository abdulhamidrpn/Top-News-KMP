package com.rpn.newskmpapp.data.local
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection

/**
 * Automatic schema migrations sometimes require extra instructions to perform the migration, for
 * example, when a column is renamed. These extra instructions are placed here by creating a class
 * using the following naming convention `SchemaXtoY` where X is the schema version you're migrating
 * from and Y is the schema version you're migrating to. The class should implement
 * `AutoMigrationSpec`.
 */
internal object DatabaseMigrations {
    val migration1to2 = object : Migration(1, 2) {
        override fun migrate(connection: SQLiteConnection) {

        }
    }
}