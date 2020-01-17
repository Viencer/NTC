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
            if (taskList.getTask(index).isRepeated()) {                         //задание, которое повтор
                int taskChooseRep = ((ChangeTaskView) view).taskChooseRep();
                if (taskChooseRep == ChooseNum.FIRST) {
                    String titleNew = ((ChangeTaskView) view).titleNew();
                    taskList.getTask(index).setTitle(titleNew);
                } else if (taskChooseRep == ChooseNum.SECOND) {
                    LocalDateTime startTime = ((ChangeTaskView) view).startTime();
                    LocalDateTime endTime = ((ChangeTaskView) view).endTime();
                    taskList.getTask(index).setStartTime(startTime);
                    taskList.getTask(index).setEndTime(endTime);
                } else if (taskChooseRep == ChooseNum.THIRD) {
                    int interval = ((ChangeTaskView) view).interval();
                    taskList.getTask(index).setRepeatInterval(interval);
                } else if (taskChooseRep == ChooseNum.FOURTH) {
                    return TASK_CHANGE;
                } else {
                    System.out.println("ERROR");
                    return TASK_CHANGE;
                }
            } else if (!taskList.getTask(index).isRepeated()) {               //задание без повтора
                int taskChooseNon = ((ChangeTaskView) view).taskChooseNon();
                if (taskChooseNon == ChooseNum.FIRST) {
                    String titleNew = ((ChangeTaskView) view).titleNew();
                    taskList.getTask(index).setTitle(titleNew);
                } else if (taskChooseNon == ChooseNum.SECOND) {
                    LocalDateTime time = ((ChangeTaskView) view).time();
                    taskList.getTask(index).setTime(time);
                } else if (taskChooseNon == ChooseNum.THIRD) {
                    return TASK_CHANGE;
                } else {
                    System.out.println("ERROR");
                    return TASK_CHANGE;
                }
            }
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        }
        return view.printInfo(taskList);
    }
}
