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
        int taskChoose = ((ChangeTaskView) view).taskChoose();         // выбираем действие
        if (taskChoose == ChooseNum.FIRST) {  // если нажали 1
            int index = ((ChangeTaskView) view).index(); // получаем индекс задания
            if (index == Integer.MAX_VALUE || taskList.size() <= 0 || taskList.size() - 1 < index) { // ловим ошибку
                System.out.println(Errors.UNEXPECTED_INDEX);
                return TASK_CHANGE;
            }
            if (taskList.getTask(index).isRepeated()) {
                repChange(index, taskList);//задание, которое повтор
            } else if (!taskList.getTask(index).isRepeated()) {               //задание без повтора
                nonRepChange(index, taskList);
            }
        } else if (taskChoose == ChooseNum.SECOND) {       // если в начале выбрали 2
            return MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.WRONG_NUMBER);
        }
        return view.printInfo(taskList);
    }

    private int nonRepChange(int index, AbstractTaskList taskList) {
        int taskChooseNon = ((ChangeTaskView) view).taskChooseNon();
        if (taskChooseNon == ChooseNum.FIRST) { // если выбрали 2.1
            nameChange(index, taskList);
        } else if (taskChooseNon == ChooseNum.SECOND) {  // если выбрали 2.2
            timeChangeNon(index, taskList);
        } else if (taskChooseNon == ChooseNum.THIRD) {   //3.3 выходим в меню
            return TASK_CHANGE;
        } else {
            System.out.println(Errors.WRONG_NUMBER);
            return TASK_CHANGE;
        }
        return TASK_CHANGE;
    }

    private int repChange(int index, AbstractTaskList taskList) {
        int taskChooseRep = ((ChangeTaskView) view).taskChooseRep();  // вывод меню для повтор. задания
        if (taskChooseRep == ChooseNum.FIRST) {  //если выбор 1.1
            nameChange(index, taskList);// меняем имя
        } else if (taskChooseRep == ChooseNum.SECOND) {     //если выбор 1.2
            timeChangeRep(index, taskList);
        } else if (taskChooseRep == ChooseNum.THIRD) {  // если выбрали 1.3
            int interval = ((ChangeTaskView) view).interval();    // вводим интевал
            if (interval == Integer.MAX_VALUE || interval <= 0) {  // ловим ошибку
                System.out.println(Errors.UNEXPECTED_INTERVAL);
                return TASK_CHANGE;
            }
            taskList.getTask(index).setRepeatInterval(interval);  // изменяем интервал
        } else if (taskChooseRep == ChooseNum.FOURTH) {
            return TASK_CHANGE;
        } else {
            System.out.println(Errors.WRONG_NUMBER);
            return TASK_CHANGE;
        }
        return TASK_CHANGE;
    }

    private void nameChange(int index, AbstractTaskList taskList) {
        String titleNew = ((ChangeTaskView) view).titleNew();
        taskList.getTask(index).setTitle(titleNew);
    }

    private int timeChangeRep(int index, AbstractTaskList taskList) {
        LocalDateTime startTime = ((ChangeTaskView) view).startTime();   // вводим время старта
        if (startTime.isBefore(LocalDateTime.now())) {       // ловим ошибку
            timeError();
            return TASK_CHANGE;
        }
        LocalDateTime endTime = ((ChangeTaskView) view).endTime(); // вводим время конца
        if ((endTime.isBefore(LocalDateTime.now()))) {         // ловим ошибку
            timeError();
            return TASK_CHANGE;
        }
        taskList.getTask(index).setStartTime(startTime);   // изменяем время
        taskList.getTask(index).setEndTime(endTime);
        return TASK_CHANGE;
    }

    private int timeChangeNon(int index, AbstractTaskList taskList) {
        LocalDateTime time = ((ChangeTaskView) view).time();  // изменяем время
        if (time.isBefore(LocalDateTime.now())) {   //ловим ошибку
            timeError();
            return TASK_CHANGE;
        }
        taskList.getTask(index).setTime(time);
        return TASK_CHANGE;
    }

    private void timeError() {
        System.out.println(Errors.UNEXPECTED_TIME);
    }
}
