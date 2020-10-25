package apps.nocturnuslabs.tasktracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import apps.nocturnuslabs.tasktracker.databinding.TaskItemBinding

class TasksListAdapter(private var onEdit: (Tasks) -> Unit, private var onDelete: (Tasks) -> Unit) :
    ListAdapter<Tasks, TasksListAdapter.TasksListViewHolder>(TaskDiffCallback()) {


    class TasksListViewHolder(
        private val binding: TaskItemBinding,
        private val onEdit: (Tasks) -> Unit,
        private val onDelete: (Tasks) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var taskID: Long = -1
        private var nameView = binding.name
        private var description = binding.description
        private var thumbnail = binding.thumbnail
        private var priority = binding.priority
        private var task: Tasks? = null

        fun bind(task: Tasks) {
            taskID = task.id
            nameView.text = task.name
            description.text = task.description
            priority.text = task.priority.toString()
            thumbnail.setImageResource(R.drawable.tasks_icon)
            this.task = task
            binding.deleteButton.setOnClickListener {
                onDelete(task)
            }
            binding.root.setOnClickListener {
                onEdit(task)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListViewHolder {
        return TasksListViewHolder(
            TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onEdit,
            onDelete
        )
    }

    override fun onBindViewHolder(holder: TasksListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Tasks>() {
    override fun areItemsTheSame(oldItem: Tasks, newItem: Tasks): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tasks, newItem: Tasks): Boolean {
        return oldItem == newItem
    }

}