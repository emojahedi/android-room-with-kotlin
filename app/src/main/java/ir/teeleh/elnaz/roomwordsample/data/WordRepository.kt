package ir.teeleh.elnaz.roomwordsample.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    suspend fun insert(word: Word) {
        withContext(IO) {
            wordDao?.insert(word)
        }
    }
}

