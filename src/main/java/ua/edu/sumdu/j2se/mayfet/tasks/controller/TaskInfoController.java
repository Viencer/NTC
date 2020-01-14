package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.TaskInfoView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class TaskInfoController extends Controller {

    public TaskInfoController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int index;
        int taskChoose = ((TaskInfoView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {
            index = ((TaskInfoView) view).index();
            if (index >= taskList.size()) {
                System.out.println("ERROR UNEXPECTED INDEX");
                return TASK_INFO;
            }
            System.out.println(taskList.getTask(index));
            return TASK_INFO;
        } else if (taskChoose == ChooseNum.SECOND) {
            index = ((TaskInfoView) view).index();
            if (index >= taskList.size()) {
                System.out.println("ERROR UNEXPECTED INDEX");
                return TASK_INFO;
            } else {
                int mode = ((TaskInfoView) view).activityMode();
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
                }
            }
        }
        return view.printInfo(taskList);
    }
}
