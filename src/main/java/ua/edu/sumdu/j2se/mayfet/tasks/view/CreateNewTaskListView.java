package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

public class CreateNewTaskListView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Журнал");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).isRepeated()) {
                System.out.println("id " + i + ". Имя задачи = " +
                        taskList.getTask(i).getTitle() + ", время начала = " + taskList.getTask(i).getStartTime() +
                        ", время окончания = " + taskList.getTask(i).getEndTime() +
                        ", интервал = " + taskList.getTask(i).getRepeatInterval() + ", активность = " +
                        taskList.getTask(i).isActive());
            } else {
                System.out.println("id " + i + ". Имя задачи = " + taskList.getTask(i).getTitle() +
                        ", время = " + taskList.getTask(i).getTime() + ", активность = " +
                        taskList.getTask(i).isActive());
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
