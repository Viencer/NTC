package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.RemoveTaskView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

public class RemoveTaskController extends Controller {
    public RemoveTaskController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((RemoveTaskView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {
            int index = ((RemoveTaskView) view).removeTask();
            if (index == Integer.MAX_VALUE || taskList.size() <= 0) {
                System.out.println(Errors.ERROR6);
                return REMOVE_TASK_ACTION;
            }
            taskList.remove(taskList.getTask(index));
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.ERROR4);
            return Controller.REMOVE_TASK_ACTION;
        }
        return view.printInfo(taskList);
    }
}
