package com.hanocybous.backend.controller;

import com.hanocybous.backend.task.TaskManager;

public class MainControllerFactory {

    public IMainController createMainController() {
        return TaskManager.getInstance();
    }

}
