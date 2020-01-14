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
        int taskChoose = ((AddTaskView) view).taskChoose();
        if (taskChoose == 1) {
            String name = ((AddTaskView) view).nameTask();
            LocalDateTime time = ((AddTaskView) view).timeTask();
            Task task1 = new Task(name, time);
            taskList.add(task1);
        } else if (taskChoose == 2) {
            String name = ((AddTaskView) view).nameTask();
            LocalDateTime timeStart = ((AddTaskView) view).timeTaskStart();
            LocalDateTime timeEnd = ((AddTaskView) view).timeTaskEnd();
            int interval = ((AddTaskView) view).repeatInterval();
            Task task2 = new Task(name, timeStart, timeEnd, interval);
            taskList.add(task2);
        } else {
            System.out.println("ERROR you choose wrong number");
            return Controller.MAIN_MENU_ACTION;
        }
        return view.printInfo(taskList);
    }
}
