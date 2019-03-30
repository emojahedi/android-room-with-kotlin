package ir.teeleh.elnaz.roomwordsample.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class WordRepository {

    private var wordDao: WordDao? = null
    private var allWords: LiveData<List<Word>>? = null

    constructor(db: WordRoomDatabase) {
        wordDao = db.wordDao()
        allWords = wordDao?.getAllWords()
    }


    fun getAllWords(): LiveData<List<Word>>? {
        return allWords
    }

    suspend fun insert(word: Word) {
        withContext(IO) {
            wordDao?.insert(word)
        }
    }
}

