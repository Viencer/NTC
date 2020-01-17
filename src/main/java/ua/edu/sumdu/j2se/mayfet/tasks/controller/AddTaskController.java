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
        if (taskChoose == ChooseNum.FIRST) {   //Если выбрана 1 то добавляем имя и время в не повтор. конструктор
            String name = ((AddTaskView) view).nameTask();
            LocalDateTime time = ((AddTaskView) view).timeTask();
            if (time.isBefore(LocalDateTime.now())) {  // ловим ошибку задача не может быть раньше поточного времени
                System.out.println(Errors.ERROR1);
                return ADD_TASK_ACTION;   // выход в меню выбора добавления задач
            }
            task = new Task(name, time);
            taskList.add(task);
        } else if (taskChoose == ChooseNum.SECOND) {   //Если выбрана 2 то добавляем имя, время, интервал в повтор. конструктор
            String name = ((AddTaskView) view).nameTask();
            LocalDateTime timeStart = ((AddTaskView) view).timeTaskStart(); // получаем начало
            LocalDateTime timeEnd = ((AddTaskView) view).timeTaskEnd(); // получаем конец
            int interval = ((AddTaskView) view).repeatInterval(); //получаем интревал
            if (timeStart.isBefore(LocalDateTime.now())) { //ловим ошибку задача не может быть раньше поточного времени
                System.out.println(Errors.ERROR1);
                return ADD_TASK_ACTION;
            }
            if ((timeEnd.isBefore(LocalDateTime.now()))) { //ловим ошибку конец задачи не может быть раньше поточного времени
                System.out.println(Errors.ERROR2);
                return ADD_TASK_ACTION;
            }
            if (interval == Integer.MAX_VALUE || interval <= 0) { //ловим ошибку если ввели неправильный интервал
                System.out.println(Errors.ERROR3);
                return ADD_TASK_ACTION;
            }
            task = new Task(name, timeStart, timeEnd, interval);  // создаём задачу
            taskList.add(task);
        } else if (taskChoose == ChooseNum.THIRD) {  // выход в главное меню
            return Controller.MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.ERROR4);    // ошика на другие числа
            return Controller.ADD_TASK_ACTION;
        }
        return view.printInfo(taskList);   // вывод
    }
}
