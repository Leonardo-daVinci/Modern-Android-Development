package apps.nocturnuslabs.tasktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import apps.nocturnuslabs.tasktracker.databinding.TaskEntryDialogBinding

class TasksEntryDialogFragment : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = TaskEntryDialogBinding.bind(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TaskEntryDialogBinding.inflate(inflater, container, false).root
    }
}