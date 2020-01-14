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
            if (index > taskList.size()) {
                System.out.println("ERROR UNEXPECTED INDEX");
                return REMOVE_TASK_ACTION;
            }
            taskList.remove(taskList.getTask(index));
        } else if (taskChoose == ChooseNum.SECOND) {
            return MAIN_MENU_ACTION;
        } else {
            System.out.println("ERROR YOU CHOOSE WRONG NUMBER");
            return Controller.ADD_TASK_ACTION;
        }
        return view.printInfo(taskList);
    }
}
