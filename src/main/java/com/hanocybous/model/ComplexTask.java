package com.hanocybous.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ComplexTask extends Task {

    private List<Task> subTasks = new ArrayList<>();

    public ComplexTask(int id, String name, int mamaId) {
        super (id,name,mamaId);
    }

    public void addSubTask(Task subTask) {
        if(this.getStart() == -1 && this.getEnd() == -1) {
            setStart(subTask.getStart());
            setEnd(subTask.getEnd());
        }
        else  {
            if(getStart() > subTask.getStart() ) {
                setStart(subTask.getStart());
            }
            if(getEnd() < subTask.getEnd()) {
                setEnd(subTask.getEnd());
            }
        }
        addCost(subTask.getCost());
    }

    public void removeSubTask(@NotNull Task subTask) {
        removeCost(subTask.getCost());
        if (subTask.getStart() == getStart() || subTask.getEnd() == getEnd()) {
            setStart(-1);
            setEnd(-1);
        }
        else {
            setStart(-1);
            setEnd(-1);
        }
        for (Task task : getSubTasks()) {
            if (task.getStart() != -1 && task.getEnd() != -1) {
                if (getStart() == -1 || getStart() > task.getStart()) {
                    setStart(task.getStart());
                }
                if (getEnd() == -1 || getEnd() < task.getEnd()) {
                    setEnd(task.getEnd());
                }
            }
        }
    }

    private List<Task> getSubTasks() {
        return subTasks;
    }

    private void removeCost(double cost) {
        addCost(getCost() - cost);
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }
}
