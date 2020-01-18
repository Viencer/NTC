package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.RemoveTaskView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

public class RemoveTaskController extends Controller {
    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public RemoveTaskController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((RemoveTaskView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) { // если выбрали 1
            int index = ((RemoveTaskView) view).removeTask(); // получаем индекс
            if (index == Integer.MAX_VALUE || taskList.size() <= 0 || taskList.size() < index) { // ловим ошибку
                logger.error(Errors.UNEXPECTED_INDEX);
                System.out.println(Errors.UNEXPECTED_INDEX);
                return REMOVE_TASK_ACTION;
            }
            taskList.remove(taskList.getTask(index)); // удаляем задание
        } else if (taskChoose == ChooseNum.SECOND) { // если выбрали 2 то выходим в главное меню
            return MAIN_MENU_ACTION;
        } else {
            logger.error(Errors.WRONG_NUMBER);
            System.out.println(Errors.WRONG_NUMBER);
            return Controller.REMOVE_TASK_ACTION;
        }
        return view.printInfo(taskList);
    }
}
