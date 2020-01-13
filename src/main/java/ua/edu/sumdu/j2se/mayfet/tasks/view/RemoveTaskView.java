package ua.edu.sumdu.j2se.mayfet.tasks.view;


import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class RemoveTaskView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("task removed");
        return Controller.MAIN_MENU_ACTION;
    }

    public int removeTask() {
        System.out.println("Put index");
        int index = 0;
        try {
            String indexIn = reader.readLine();
            index = Integer.parseInt(indexIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }
}
