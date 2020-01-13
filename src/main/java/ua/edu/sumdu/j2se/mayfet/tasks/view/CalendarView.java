package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

public class CalendarView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("calendar view");
        return Controller.MAIN_MENU_ACTION;
    }
}
