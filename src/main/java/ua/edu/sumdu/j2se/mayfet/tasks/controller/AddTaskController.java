package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
import ua.edu.sumdu.j2se.mayfet.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.mayfet.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AddTaskController extends Controller {
    private static final Logger logger = Logger.getLogger(NotificationController.class);

    public AddTaskController(View view, int actionToPerForm) {
        super(view, actionToPerForm);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        Task task;
        int taskChoose = ((AddTaskView) view).taskChoose();
        if (taskChoose == ChooseNum.FIRST) {   //Если выбрана 1 то добавляем имя и время в не повтор. конструктор
            nonRepTask(taskList);
        } else if (taskChoose == ChooseNum.SECOND) {   //Если выбрана 2 то добавляем имя, время, интервал в повтор. конструктор
            repTask(taskList);
        } else if (taskChoose == ChooseNum.THIRD) {  // выход в главное меню
            return Controller.MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.WRONG_NUMBER);// ошибка на другие числа
            logger.error(Errors.WRONG_NUMBER);
            return Controller.ADD_TASK_ACTION;
        }
        return view.printInfo(taskList);   // вывод
    }

    private int repTask(AbstractTaskList taskList) {
        Task task;
        String name = ((AddTaskView) view).nameTask();
        LocalDateTime timeStart = ((AddTaskView) view).timeTaskStart(); // получаем начало
        LocalDateTime timeEnd = ((AddTaskView) view).timeTaskEnd(); // получаем конец
        int interval = ((AddTaskView) view).repeatInterval(); //получаем интревал
        if (timeStart.isBefore(LocalDateTime.now())) { //ловим ошибку задача не может быть раньше поточного времени
            logger.error(Errors.UNEXPECTED_TIME);
            System.out.println(Errors.UNEXPECTED_TIME);
            return ADD_TASK_ACTION;
        }
        if ((timeEnd.isBefore(LocalDateTime.now()))) { //ловим ошибку конец задачи не может быть раньше поточного времени
            logger.error(Errors.UNEXPECTED_END_TIME);
            System.out.println(Errors.UNEXPECTED_END_TIME);
            return ADD_TASK_ACTION;
        }
        if (interval == Integer.MAX_VALUE || interval <= 0) { //ловим ошибку если ввели неправильный интервал
            logger.error(Errors.UNEXPECTED_INTERVAL);
            System.out.println(Errors.UNEXPECTED_INTERVAL);
            return ADD_TASK_ACTION;
        }
        task = new Task(name, timeStart, timeEnd, interval);  // создаём задачу
        task.setActive(true);
        taskList.add(task);
        saveDir(taskList);
        return ADD_TASK_ACTION;
    }

    private int nonRepTask(AbstractTaskList taskList) {
        Task task;
        String name = ((AddTaskView) view).nameTask();
        LocalDateTime time = ((AddTaskView) view).timeTask();
        if (time.isBefore(LocalDateTime.now())) {  // ловим ошибку задача не может быть раньше поточного времени
            logger.error(Errors.UNEXPECTED_TIME);
            System.out.println(Errors.UNEXPECTED_TIME);
            return ADD_TASK_ACTION;   // выход в меню выбора добавления задач
        }
        task = new Task(name, time);
        task.setActive(true);
        taskList.add(task);
        saveDir(taskList);
        return ADD_TASK_ACTION;
    }

    private void saveDir(AbstractTaskList taskList) {
        try {
            File directory = new File("saves");  // создаём директорию сохранений
            directory.mkdir();
            TaskIO.write(taskList, new FileWriter(new File("saves/autoSave.json")));// сохранение в формате json
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
