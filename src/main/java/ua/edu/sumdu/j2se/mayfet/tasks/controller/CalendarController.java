package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

public class CalendarController extends Controller {
    private static final Logger logger = Logger.getLogger(NotificationController.class);

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
            System.out.println(Errors.WRONG_NUMBER);
            logger.error(Errors.WRONG_NUMBER);
            return CALENDAR_ACTION;
        }
    }
}
