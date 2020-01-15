package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Tasks;
import ua.edu.sumdu.j2se.mayfet.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CalendarController extends Controller {
    public CalendarController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((CalendarView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {
            LocalDateTime timeStart = ((CalendarView) view).timeTaskStart();
            LocalDateTime timeEnd = ((CalendarView) view).timeTaskEnd();
            if ((timeStart.isEqual(LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999)))) {
                System.out.println("ERROR UNEXPECTED TIME");
                return MAIN_MENU_ACTION;
            }
            if ((timeEnd.isBefore(LocalDateTime.now()))) {
                System.out.println("ERROR UNEXPECTED END TIME");
                return MAIN_MENU_ACTION;
            }
            Tasks.calendar(taskList, timeStart, timeEnd);
        }
        else {
            return MAIN_MENU_ACTION;
        }
        return view.printInfo(taskList);
    }
}
