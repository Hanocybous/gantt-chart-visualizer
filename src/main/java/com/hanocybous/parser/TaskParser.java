package com.hanocybous.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.hanocybous.model.ComplexTask;
import com.hanocybous.model.SimpleTask;
import com.hanocybous.model.Task;
import org.jetbrains.annotations.NotNull;

public class TaskParser {
    private ArrayList<Task> returnTaskList;

    public TaskParser() {
        this.returnTaskList = new ArrayList<>();
    }

    public List<Task> getReturnTaskList() {
        return returnTaskList;
    }

    public void parseLine(@NotNull String line, String delimiter) {
        ArrayList<String> taskData = new ArrayList<>(Arrays.asList(line.split(delimiter)));
        Task tempTask;

        if (taskData.size() == 6) {
            tempTask = new SimpleTask(
                    Integer.parseInt(taskData.get(0)),
                    taskData.get(1),
                    Integer.parseInt(taskData.get(2)),
                    Integer.parseInt(taskData.get(3)),
                    Integer.parseInt(taskData.get(4)),
                    Double.parseDouble(taskData.get(5))
            );
            returnTaskList.add(tempTask);
        } else if (taskData.size() == 3) {
            tempTask = new ComplexTask(
                    Integer.parseInt(taskData.get(0)),
                    taskData.get(1),
                    Integer.parseInt(taskData.get(2))
            );
            returnTaskList.add(tempTask);
        }

        updateComplexTasks();
    }

    private void updateComplexTasks() {
        HashMap<Integer, Integer> mamaLocation = new HashMap<>();
        if (!returnTaskList.isEmpty()) {
            for (int i = 0; i < returnTaskList.size(); i++) {
                if (returnTaskList.get(i).getMamaId() == 0) {
                    mamaLocation.put(returnTaskList.get(i).getId(), i);
                }
            }
            for (Task task : returnTaskList) {
                if (task.getMamaId() != 0) {
                    Integer temp = mamaLocation.get(task.getMamaId());
                    if (temp != null) {
                        returnTaskList.get(temp).addSubTask(task);
                    }
                }
            }
        }
    }
}