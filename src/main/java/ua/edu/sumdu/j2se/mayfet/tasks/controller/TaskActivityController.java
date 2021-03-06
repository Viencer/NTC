package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.TaskActivityView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class TaskActivityController extends Controller {
    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public TaskActivityController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int index;
        int taskChoose = ((TaskActivityView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {  // если выбрали 1, то получаем индекс
            index = ((TaskActivityView) view).index();
            if (index == Integer.MAX_VALUE || taskList.size() <= 0 || taskList.size() - 1 < index) { // ловим ошибку
                logger.error(Errors.UNEXPECTED_INDEX);
                System.out.println(Errors.UNEXPECTED_INDEX);
                return TASK_ACTIVE;
            } else {
                int mode = ((TaskActivityView) view).activityMode(); // выбираем какой сделать задачу
                if (mode == -1) {
                    logger.error(Errors.WRONG_NUMBER);
                    System.out.println(Errors.WRONG_NUMBER);
                    return TASK_ACTIVE;
                } else {
                    if (mode == ChooseNum.FIRST) {  // выбираем 1 и активируем задачу
                        taskList.getTask(index).setActive(true);
                        if ((taskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) { // если задача уже прошла, то удаляем её
                            taskList.remove(taskList.getTask(index));
                        }
                    } else if (mode == ChooseNum.SECOND) {   // выбираем 2 и деактевируем задачу
                        taskList.getTask(index).setActive(false);
                        if ((taskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {// если задача уже прошла, то удаляем её
                            taskList.remove(taskList.getTask(index));
                        }
                    } else {
                        logger.error(Errors.WRONG_NUMBER);
                        System.out.println(Errors.WRONG_NUMBER);
                        return TASK_ACTIVE;
                    }
                }
            }
        } else if (taskChoose == ChooseNum.SECOND) { // выбираем 2 и выходим в главне меню
            return MAIN_MENU_ACTION;
        } else {
            logger.error(Errors.WRONG_NUMBER);
            System.out.println(Errors.WRONG_NUMBER);
            return TASK_ACTIVE;
        }
        return view.printInfo(taskList);
    }
}
