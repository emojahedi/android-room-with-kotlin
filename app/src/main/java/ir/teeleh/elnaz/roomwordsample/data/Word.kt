package ir.teeleh.elnaz.roomwordsample.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @NonNull @ColumnInfo(name = "word") val word: String
) {

    override fun toString() = word
}
