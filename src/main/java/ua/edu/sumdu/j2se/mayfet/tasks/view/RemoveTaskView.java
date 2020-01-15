package ua.edu.sumdu.j2se.mayfet.tasks.view;


import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class RemoveTaskView implements View, TaskAction {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("task removed");
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {
        System.out.println("delete task?");
        System.out.println("1 - delete");
        System.out.println("2 - back to menu");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public int removeTask() {
        System.out.println("Put index");
        int index = 0;
        try {
            String indexIn = reader.readLine();
            index = Integer.parseInt(indexIn);
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
        return index;
    }
}
