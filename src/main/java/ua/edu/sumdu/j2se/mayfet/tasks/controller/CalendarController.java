package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Tasks;
import ua.edu.sumdu.j2se.mayfet.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.time.LocalDateTime;

public class CalendarController extends Controller {
    public CalendarController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        LocalDateTime timeStart = ((CalendarView) view).timeTaskStart();
        LocalDateTime timeEnd = ((CalendarView) view).timeTaskEnd();
        if ((timeStart.isBefore(LocalDateTime.now()))) {
            System.out.println("ERROR UNEXPECTED TIME");
            return MAIN_MENU_ACTION;
        }
        if ((timeEnd.isBefore(LocalDateTime.now()))) {
            System.out.println("ERROR UNEXPECTED END TIME");
            return MAIN_MENU_ACTION;
        }
        Tasks.calendar(taskList, timeStart, timeEnd);
        return view.printInfo(taskList);
    }
}
