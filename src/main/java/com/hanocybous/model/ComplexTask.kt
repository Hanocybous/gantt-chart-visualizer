package com.hanocybous.model

import org.jetbrains.annotations.NotNull

class ComplexTask(
    id: Int,
    name: String,
    mamaId: Int
) : Task(id, name, mamaId) {

    private var subTasks: MutableList<Task> = ArrayList()

    override fun addSubTask(subTask: Task) {
        if (this.start == -1 && this.end == -1) {
            start = subTask.start
            end = subTask.end
        } else {
            if (start > subTask.start) {
                start = subTask.start
            }
            if (end < subTask.end) {
                end = subTask.end
            }
        }
        addCost(subTask.cost)
    }

    fun removeSubTask(@NotNull subTask: Task) {
        removeCost(subTask.cost)
        start = -1
        end = -1
        for (task in subTasks) {
            if (task.start != -1 && task.end != -1) {
                if (start == -1 || start > task.start) {
                    start = task.start
                }
                if (end == -1 || end < task.end) {
                    end = task.end
                }
            }
        }
    }

    private fun getSubTasks(): List<Task> {
        return subTasks
    }

    private fun removeCost(cost: Double) {
        addCost(cost - cost)
    }

    fun setSubTasks(subTasks: MutableList<Task>) {
        this.subTasks = subTasks
    }

}