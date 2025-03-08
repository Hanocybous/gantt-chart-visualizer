package com.hanocybous.model

abstract class Task(
    var id: Int,
    var name: String,
    var mamaId: Int,
    var start: Int = -1,
    var end: Int = -1,
    var cost: Double = 0.0
) {
    var complexTaskCheck: Boolean = true

    constructor(id: Int, name: String, mamaId: Int) : this(id, name, mamaId, -1, -1, 0.0)

    abstract fun addSubTask(subTask: Task)

    fun stringTask(): Array<String> {
        return arrayOf(id.toString(), name, mamaId.toString(), start.toString(), end.toString(), cost.toString())
    }

    fun setTaskDescription(newValue: String) {
        this.name = newValue
    }

    // Renamed method to avoid conflict
    fun updateMamaId(parseInt: Int) {
        this.mamaId = parseInt
    }

    fun setTaskId(parseInt: Int) {
        this.id = parseInt
    }

    protected fun addCost(cost: Double) {
        this.cost += cost
    }
}