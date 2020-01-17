package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

public class CalendarController extends Controller {
    public CalendarController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((CalendarView) view).taskChoose(); // Здесь ловим ошибки
        if (taskChoose == ChooseNum.FIRST) {
            return view.printInfo(taskList);
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.ERROR4);
            return CALENDAR_ACTION;
        }
    }
}
