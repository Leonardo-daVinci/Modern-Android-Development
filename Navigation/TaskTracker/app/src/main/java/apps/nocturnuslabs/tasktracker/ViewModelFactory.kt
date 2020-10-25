package apps.nocturnuslabs.tasktracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import apps.nocturnuslabs.tasktracker.storage.TasksDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val tasksDao: TasksDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksListViewModel::class.java)) {
            return TasksListViewModel(tasksDao) as T
        } else if (modelClass.isAssignableFrom(TasksEntryViewModel::class.java)) {
            return TasksEntryViewModel(tasksDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}