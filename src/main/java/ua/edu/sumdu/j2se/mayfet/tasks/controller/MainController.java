package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<Controller>();

    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new CreateNewTaskListController(new CreateNewTaskListView(), Controller.TASK_LIST_ACTION));
        controllers.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));
        controllers.add(new SaveLoadTasksController(new SaveLoadTasksView(), Controller.SAVE_LOAD_TASKS));
        controllers.add(new TaskActivityController(new TaskActivityView(), Controller.TASK_INFO));
        NotificationController notify = new NotificationController(new NotificationView(), taskList);
        notify.setDaemon(true);
        notify.start();
    }

    @Override
    public int process(AbstractTaskList taskList) throws IOException {
        int action = view.printInfo(taskList);
        if (action == 1 || action == 2 || action == 3 || action == 4 || action == 5 || action == 6 || action == 7) {
            for (; ; ) {
                for (Controller controller : controllers) {
                    if (controller.canProcess(action)) {
                        action = controller.process(this.taskList);
                    }
                }
                if (action == FINISH_ACTION) {
                    break;
                }
            }
        }
        return FINISH_ACTION;
    }
}
