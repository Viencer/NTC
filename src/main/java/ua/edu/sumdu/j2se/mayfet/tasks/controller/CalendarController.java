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
            return view.printInfo(taskList);
        } else {
            return MAIN_MENU_ACTION;
        }
    }
}
