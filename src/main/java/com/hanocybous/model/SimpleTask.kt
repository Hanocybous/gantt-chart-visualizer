package com.hanocybous.model

class SimpleTask(
    id: Int,
    name: String,
    mamaId: Int,
    start: Int,
    end: Int,
    cost: Double
) : Task(id, name, mamaId, start, end, cost) {

    override fun addSubTask(subTask: Task) {
        // Simple Tasks can't have subtasks
    }
}