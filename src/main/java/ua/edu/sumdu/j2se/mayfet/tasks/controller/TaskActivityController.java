package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.TaskActivityView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class TaskActivityController extends Controller {

    public TaskActivityController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int index;
        int taskChoose = ((TaskActivityView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {
            index = ((TaskActivityView) view).index();
            if (index == Integer.MAX_VALUE || taskList.size() <= 0 || taskList.size() - 1 < index) {
                System.out.println(Errors.ERROR6);
                return TASK_ACTIVE;
            } else {
                int mode = ((TaskActivityView) view).activityMode();
                if (mode == -1) {
                    System.out.println(Errors.ERROR4);
                    return TASK_ACTIVE;
                } else {
                    if (mode == ChooseNum.FIRST) {
                        taskList.getTask(index).setActive(true);
                        if ((taskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {
                            taskList.remove(taskList.getTask(index));
                        }
                    } else if (mode == ChooseNum.SECOND) {
                        taskList.getTask(index).setActive(false);
                        if ((taskList.getTask(index).getEndTime()).isBefore(LocalDateTime.now())) {
                            taskList.remove(taskList.getTask(index));
                        }
                    } else {
                        System.out.println(Errors.ERROR4);
                        return TASK_ACTIVE;
                    }
                }
            }
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.ERROR4);
            return TASK_ACTIVE;
        }
        return view.printInfo(taskList);
    }
}
