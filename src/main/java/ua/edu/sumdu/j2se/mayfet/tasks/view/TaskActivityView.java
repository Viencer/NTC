package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class TaskActivityView implements View, TaskAction {
    @Override
    public int taskChoose() {
        System.out.println("Task activity");
        System.out.println("1 - set active/inactive mode,  2 - back to menu");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Task activity setting closed");
        return Controller.MAIN_MENU_ACTION;
    }

    public int index() {
        int index = 0;
        try {
            System.out.print("choose task on index ");
            index = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return index;
    }

    public int activityMode() {
        int mode = 0;
        try {
            System.out.println("set activity");
            System.out.println("1 - active mode");
            System.out.println("2 - inactive mode");
            mode = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return mode;
    }
}
