package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;

public class ChangeTaskController extends Controller {
    public ChangeTaskController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int taskChoose = ((ChangeTaskView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {
            int index = ((ChangeTaskView) view).index();
            if (index == Integer.MAX_VALUE || taskList.size() <= 0 || taskList.size() - 1 < index) {
                System.out.println(Errors.ERROR6);
                return TASK_CHANGE;
            }
            if (taskList.getTask(index).isRepeated()) {                         //задание, которое повтор
                int taskChooseRep = ((ChangeTaskView) view).taskChooseRep();
                if (taskChooseRep == ChooseNum.FIRST) {
                    String titleNew = ((ChangeTaskView) view).titleNew();
                    taskList.getTask(index).setTitle(titleNew);
                } else if (taskChooseRep == ChooseNum.SECOND) {
                    LocalDateTime startTime = ((ChangeTaskView) view).startTime();
                    if (startTime.isBefore(LocalDateTime.now())) {
                        System.out.println(Errors.ERROR1);
                        return TASK_CHANGE;
                    }
                    LocalDateTime endTime = ((ChangeTaskView) view).endTime();
                    if ((endTime.isBefore(LocalDateTime.now()))) {
                        System.out.println(Errors.ERROR2);
                        return TASK_CHANGE;
                    }
                    taskList.getTask(index).setStartTime(startTime);
                    taskList.getTask(index).setEndTime(endTime);
                } else if (taskChooseRep == ChooseNum.THIRD) {
                    int interval = ((ChangeTaskView) view).interval();
                    if (interval == Integer.MAX_VALUE || interval <= 0) {
                        System.out.println(Errors.ERROR3);
                        return TASK_CHANGE;
                    }
                    taskList.getTask(index).setRepeatInterval(interval);
                } else if (taskChooseRep == ChooseNum.FOURTH) {
                    return TASK_CHANGE;
                } else {
                    System.out.println(Errors.ERROR4);
                    return TASK_CHANGE;
                }
            } else if (!taskList.getTask(index).isRepeated()) {               //задание без повтора
                int taskChooseNon = ((ChangeTaskView) view).taskChooseNon();
                if (taskChooseNon == ChooseNum.FIRST) {
                    String titleNew = ((ChangeTaskView) view).titleNew();
                    taskList.getTask(index).setTitle(titleNew);
                } else if (taskChooseNon == ChooseNum.SECOND) {
                    LocalDateTime time = ((ChangeTaskView) view).time();
                    if (time.isBefore(LocalDateTime.now())) {
                        System.out.println(Errors.ERROR1);
                        return TASK_CHANGE;
                    }
                    taskList.getTask(index).setTime(time);
                } else if (taskChooseNon == ChooseNum.THIRD) {
                    return TASK_CHANGE;
                } else {
                    System.out.println(Errors.ERROR4);
                    return TASK_CHANGE;
                }
            }
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.ERROR4);
        }
        return view.printInfo(taskList);
    }
}
