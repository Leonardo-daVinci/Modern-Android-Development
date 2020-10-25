package apps.nocturnuslabs.tasktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import apps.nocturnuslabs.tasktracker.databinding.TasksListBinding
import apps.nocturnuslabs.tasktracker.storage.TasksDatabase
import kotlinx.android.synthetic.main.tasks_list.*

class TasksList : Fragment() {

    private lateinit var tasksListViewModel: TasksListViewModel

    private val adapter = TasksListAdapter(
        onEdit = {
            findNavController().navigate(
                TasksListDirections.actionTasksListToTasksEntryDialogFragment(
                    it.id
                )
            )
        },
        onDelete = {
            NotificationManagerCompat.from(requireContext()).cancel(it.id.toInt())
            tasksListViewModel.delete(it)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = TasksListBinding.bind(view)
        val tasksDao = TasksDatabase.getDatabase(requireContext()).tasksDao()
        tasksListViewModel =
            ViewModelProvider(this, ViewModelFactory(tasksDao)).get(TasksListViewModel::class.java)

        tasksListViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }

        recyclerView.adapter = adapter

        binding.fab.setOnClickListener { fabview ->
            fabview.findNavController()
                .navigate(TasksListDirections.actionTasksListToTasksEntryDialogFragment())
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TasksListBinding.inflate(inflater, container, false).root
    }
}