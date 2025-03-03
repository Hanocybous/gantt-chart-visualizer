package com.hanocybous;

public final class SimpleTask extends Task {

    public SimpleTask(int id, String name, int mamaId, int start, int end, double d) {
        super(id,name,mamaId,start,end,d);
    }

    public void addSubTask(Task subTask) { /*Simple Tasks can't have subtasks*/ }
}
