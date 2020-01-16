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
            if (index >= taskList.size() || index == -1) {
                System.out.println("ERROR UNEXPECTED INDEX");
                return TASK_INFO;
            } else {
                int mode = ((TaskActivityView) view).activityMode();
                if (mode == -1) {
                    System.out.println("ERROR UNEXPECTED NUM");
                    return TASK_INFO;
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
                        System.out.println("ERROR UNEXPECTED INDEX");
                        return TASK_INFO;
                    }
                }
            }
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        } else {
            System.out.println("ERROR YOU CHOOSE WRONG NUMBER");
            return TASK_INFO;
        }
        return view.printInfo(taskList);
    }
}
