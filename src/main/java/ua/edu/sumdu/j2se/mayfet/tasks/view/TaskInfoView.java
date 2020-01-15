package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.ChooseNum;
import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class TaskInfoView implements View, TaskAction {
    @Override
    public int taskChoose() {
        System.out.println("Task info");
        System.out.println("Put action");
        System.out.println("1 - view task on index");
        System.out.println("2 - set active/inactive mode");
        System.out.println("3 - back to menu");
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
        System.out.println("Task info closed");
        return Controller.MAIN_MENU_ACTION;
    }

    public int index() {
        int index = 0;
        try {
            System.out.println("choose task on index");
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
