package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
import ua.edu.sumdu.j2se.mayfet.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.time.LocalDateTime;


public class AddTaskController extends Controller {

    public AddTaskController(View view, int actionToPerForm) {
        super(view, actionToPerForm);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        Task task;
        int taskChoose = ((AddTaskView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {
            String name = ((AddTaskView) view).nameTask();
            LocalDateTime time = ((AddTaskView) view).timeTask();
            task = new Task(name, time);
            taskList.add(task);
        } else if (taskChoose == ChooseNum.SECOND) {
            String name = ((AddTaskView) view).nameTask();
            LocalDateTime timeStart = ((AddTaskView) view).timeTaskStart();
            LocalDateTime timeEnd = ((AddTaskView) view).timeTaskEnd();
            int interval = ((AddTaskView) view).repeatInterval();
            task = new Task(name, timeStart, timeEnd, interval);
            taskList.add(task);
        } else if (taskChoose == ChooseNum.THIRD) {
            return Controller.MAIN_MENU_ACTION;
        } else {
            System.out.println("ERROR YOU CHOOSE WRONG NUMBER");
            return Controller.ADD_TASK_ACTION;
        }
        return view.printInfo(taskList);
    }
}
