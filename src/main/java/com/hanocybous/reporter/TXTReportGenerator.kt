package com.hanocybous.reporter

import com.hanocybous.model.Task
import java.io.FileWriter
import java.io.IOException

class TXTReportGenerator(private val path: String, private val tasks: List<Task>) {
    companion object {
        private val columnNames = arrayOf("TaskId", "TaskText", "MamaId", "Start", "End", "Cost")
    }

    fun generate(): Int {
        return try {
            FileWriter(path).use { myWriter ->
                for (i in columnNames.indices) {
                    myWriter.write(columnNames[i] + if (i == columnNames.size - 1) "\t\n" else "\t")
                }
                for (task in tasks) {
                    myWriter.write("${task.id}\t${task.name}\t${task.mamaId}\t${task.start}\t${task.end}\t${task.cost}\t\n")
                }
            }
            0
        } catch (e: IOException) {
            1
        }
    }
}