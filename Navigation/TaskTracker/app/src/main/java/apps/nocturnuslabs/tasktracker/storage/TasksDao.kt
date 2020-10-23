package apps.nocturnuslabs.tasktracker.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import apps.nocturnuslabs.tasktracker.Tasks


@Dao
interface TasksDao {
    @Query("SELECT * FROM Tasks")
    fun getAll(): LiveData<List<Tasks>>

    @Query("SELECT * FROM Tasks where id = :id")
    suspend fun get(id: Long): Tasks

    @Insert
    suspend fun insert(task: Tasks): Long

    @Delete
    suspend fun delete(task: Tasks)

    @Update
    suspend fun update(task: Tasks)
}