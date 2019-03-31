package ir.teeleh.elnaz.roomwordsample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities = [Word::class], version = 1)

abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var instance: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WordRoomDatabase {
            return Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        populateDb(instance)
                    }
                })
                .build()
        }

        fun populateDb(db: WordRoomDatabase?) {
            GlobalScope.launch(Dispatchers.IO) {
                val wordDao = db?.wordDao()
                wordDao?.deleteAll()
                var word = Word("Hello")
                wordDao?.insert(word)
                word = Word("World")
                wordDao?.insert(word)
            }
        }
    }

}