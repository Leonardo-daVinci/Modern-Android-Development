package apps.nocturnuslabs.tasktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import apps.nocturnuslabs.tasktracker.databinding.TaskEntryDialogBinding
import apps.nocturnuslabs.tasktracker.storage.TasksDatabase

class TasksEntryDialogFragment : BottomSheetDialogFragment() {

    private lateinit var tasksEntryViewModel: TasksEntryViewModel

    private enum class EditingState {
        NEW_TASK,
        EXISTING_TASK
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tasksDao = TasksDatabase.getDatabase(requireContext()).tasksDao()
        tasksEntryViewModel = ViewModelProvider(this, ViewModelFactory(tasksDao)).get(
            TasksEntryViewModel::class.java
        )
        val binding = TaskEntryDialogBinding.bind(view)
        var task: Tasks? = null
        val args: TasksEntryDialogFragmentArgs by navArgs()
        val state =
            if (args.itemId > 0) EditingState.EXISTING_TASK
            else EditingState.NEW_TASK

        if (state == EditingState.EXISTING_TASK) {
            tasksEntryViewModel.get(args.itemId).observe(viewLifecycleOwner) {
                binding.name.setText(it.name)
                binding.description.setText(it.description)
                binding.ratingBar.rating = it.priority.toFloat()
                task = it
            }
        }

        binding.doneButton.setOnClickListener {
            val context = requireContext().applicationContext
            val navController = findNavController()

            tasksEntryViewModel.addData(
                task?.id ?: 0,
                binding.name.text.toString(),
                binding.description.text.toString(),
                binding.ratingBar.rating.toInt(),
            )
            // more stuff here.
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TaskEntryDialogBinding.inflate(inflater, container, false).root
    }
}