package om.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao

    companion object {
        val DATABASE_NAME: String = "news_db"
    }
}