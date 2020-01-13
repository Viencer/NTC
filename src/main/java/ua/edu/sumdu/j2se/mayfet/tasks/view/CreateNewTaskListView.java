package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

public class CreateNewTaskListView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("task View");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + ". " + taskList.getTask(i));
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
