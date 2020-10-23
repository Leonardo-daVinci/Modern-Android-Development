package apps.nocturnuslabs.tasktracker.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import apps.nocturnuslabs.tasktracker.Tasks

@Database(
    entities = [Tasks::class], version = 1
)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile
        private var INSTANCE: TasksDatabase? = null

        fun getDatabase(context: Context): TasksDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, TasksDatabase::class.java, "tasks_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
