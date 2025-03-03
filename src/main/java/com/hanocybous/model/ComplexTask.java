package com.hanocybous;

import org.jetbrains.annotations.NotNull;

public final class ComplexTask extends Task {

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
        if(this.getStart() == subTask.getStart() && this.getEnd() == subTask.getEnd()) {
            setStart(-1);
            setEnd(-1);
        }
        else {
            if(getStart() == subTask.getStart()) {
                setStart(subTask.getEnd());
            }
            if(getEnd() == subTask.getEnd()) {
                setEnd(subTask.getStart());
            }
        }
        removeCost(subTask.getCost());
    }

    private void removeCost(double cost) {
        addCost(getCost() - cost);
    }
}
