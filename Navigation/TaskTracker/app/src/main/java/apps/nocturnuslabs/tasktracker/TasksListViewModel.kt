package apps.nocturnuslabs.tasktracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.nocturnuslabs.tasktracker.storage.TasksDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksListViewModel(private val tasksDao: TasksDao) : ViewModel() {

    val tasks: LiveData<List<Tasks>> = tasksDao.getAll()

    fun delete(task: Tasks) = viewModelScope.launch(Dispatchers.IO) {
        tasksDao.delete(task)
    }

}