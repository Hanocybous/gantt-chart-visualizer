package com.hanocybous.model;

public abstract class Task {
    private int id;
    private String name;
    private int mamaId;
    private int start = -1;
    private int end = -1;
    private double cost;
    private boolean complexTaskCheck;

    protected Task(int id,String name, int mamaId) {
        this.id = id;
        this.name = name;
        this.mamaId = mamaId;
        complexTaskCheck = true;
    }

    protected Task(int id,String name, int mamaId, int start, int end, double cost) {
        this.id = id;
        this.name = name;
        this.mamaId = mamaId;
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMamaId() {
        return mamaId;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public double getCost() {
        return cost;
    }

    public boolean isComplexTask() {
        return complexTaskCheck;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    protected void addCost(double cost) {
        this.cost += cost;
    }

    public abstract void addSubTask(Task subTask);

    public String[] stringTask() {
        return new String[] {Integer.toString(id),name,Integer.toString(mamaId),Integer.toString(start),Integer.toString(end),Double.toString(cost)};
    }

    public void setTaskDescription(String newValue) {
        this.name = newValue;
    }

    public void setMamaId(int parseInt) {
        this.mamaId = parseInt;
    }

    public void setCost(Double mamaCost) {
        this.cost = mamaCost;
    }

    public void setTaskId(int parseInt) {
        this.id = parseInt;
    }
}
