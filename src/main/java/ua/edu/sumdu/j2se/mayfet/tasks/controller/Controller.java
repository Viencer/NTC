package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int TASK_LIST_ACTION = 1;
    public static final int ADD_TASK_ACTION = 2;
    public static final int REMOVE_TASK_ACTION = 3;
    public static final int CALENDAR_ACTION = 4;
    public static final int FINISH_ACTION = 5;

    protected View view;
    protected int actionToPerform = 0;

    public Controller(View view, int actionToPerform) {
        this.view = view;
        this.actionToPerform = actionToPerform;
    }


    public boolean canProcess(int action) {
        return action == actionToPerform;
    }

    public abstract int process(AbstractTaskList taskList);

}