package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

public class CreateNewTaskListView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("task View");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).isRepeated()) {
                System.out.println("id " + i + ". Task name = " +
                        taskList.getTask(i).getTitle() + ", start time = " + taskList.getTask(i).getStartTime() +
                        ", end time = " + taskList.getTask(i).getEndTime() +
                        ", repeat interval = " + taskList.getTask(i).getRepeatInterval() + ", active = " +
                        taskList.getTask(i).isActive());
            } else {
                System.out.println("id " + i + ". Task name = " + taskList.getTask(i).getTitle() +
                        ", time = " + taskList.getTask(i).getTime() + ", active = " +
                        taskList.getTask(i).isActive());
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
