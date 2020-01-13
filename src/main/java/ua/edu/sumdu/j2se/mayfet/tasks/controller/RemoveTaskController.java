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
        int index = ((RemoveTaskView) view).removeTask();
        taskList.remove(taskList.getTask(index));
        return view.printInfo(taskList);
    }
}
