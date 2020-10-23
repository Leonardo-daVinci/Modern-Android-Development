package apps.nocturnuslabs.tasktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import apps.nocturnuslabs.tasktracker.databinding.TasksListBinding
import kotlinx.android.synthetic.main.tasks_list.*

class TasksList : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = TasksListBinding.bind(view)
        binding.fab.setOnClickListener { fabview ->
            fabview.findNavController().navigate(R.id.action_tasksList_to_tasksEntryDialogFragment)
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