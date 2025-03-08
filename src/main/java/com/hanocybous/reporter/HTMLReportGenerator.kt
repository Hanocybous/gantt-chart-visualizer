package com.hanocybous.reporter

import com.hanocybous.model.Task
import java.io.FileWriter
import java.io.IOException

class HTMLReportGenerator(private val path: String, private val tasks: List<Task>) {
    companion object {
        private val columnNames = arrayOf("TaskId", "TaskText", "MamaId", "Start", "End", "Cost")
    }

    fun generate(): Int {
        return try {
            FileWriter(path).use { myWriter ->
                myWriter.write("<html>\n<head>\n<title>Report</title>\n</head>\n<body>\n<table border=\"1\">\n<tr>\n")
                for (columnName in columnNames) {
                    myWriter.write("<th>$columnName</th>\n")
                }
                myWriter.write("</tr>\n")
                for (task in tasks) {
                    myWriter.write("<tr>\n<td>${task.id}</td>\n<td>${task.name}</td>\n<td>${task.mamaId}</td>\n<td>${task.start}</td>\n<td>${task.end}</td>\n<td>${task.cost}</td>\n</tr>\n")
                }
                myWriter.write("</table>\n</body>\n</html>\n")
            }
            0
        } catch (e: IOException) {
            1
        }
    }
}