package apps.nocturnuslabs.tasktracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import apps.nocturnuslabs.tasktracker.storage.TasksDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksEntryViewModel(private val tasksDao: TasksDao) : ViewModel() {

    private var tasksLiveData: LiveData<Tasks>? = null

    fun get(id: Long): LiveData<Tasks> {
        return tasksLiveData ?: liveData {
            emit(tasksDao.get(id))
        }.also {
            tasksLiveData = it
        }
    }

    fun addData(id: Long, name: String, description: String, priority: Int) {
        val task = Tasks(id, name, description, priority)

        CoroutineScope(Dispatchers.Main.immediate).launch {
            var actualId = id
            if (id > 0) {
                update(task)
            } else {
                actualId = insert(task)
            }

            //setupNotification(actualId)
        }
    }

    private suspend fun insert(task: Tasks): Long {
        return tasksDao.insert(task)
    }

    private suspend fun update(task: Tasks) = viewModelScope.launch(Dispatchers.IO) {
        tasksDao.update(task)
    }

}