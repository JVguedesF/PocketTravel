package br.com.renankoji.pockettravel.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.renankoji.pockettravel.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDB : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        private lateinit var instance: UserDB

        fun getDatabase(context: Context): UserDB {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        UserDB::class.java,
                        "user_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}