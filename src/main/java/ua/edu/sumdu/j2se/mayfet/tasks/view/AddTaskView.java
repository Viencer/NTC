package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTaskView implements View, TaskAction {

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Задание было добавлено");
        return Controller.ADD_TASK_ACTION;
    }

    @Override
    public int taskChoose() {  //выбор пункта меню
        System.out.println("введите тип задачи");
        System.out.println("1 - не повторяющаяся,  2 - повторяющаяся,  3 - выход в меню");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public String nameTask() {    //ввод имени
        System.out.print("введите имя задачи = ");
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public LocalDateTime timeTask() {                                 //ввод времени
        System.out.print("введите дату (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public LocalDateTime timeTaskStart() {                          //ввод времени начала
        System.out.print("введите дату начала (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public LocalDateTime timeTaskEnd() {                                //ввод времени конца
        System.out.print("введите дату окончания (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public int repeatInterval() {                            //ввод интервала
        System.out.print("введите интервал в минутах = ");
        int interval = 0;
        try {
            String indexIn = reader.readLine();
            interval = Integer.parseInt(indexIn);
        } catch (IOException | NumberFormatException e) {
            interval = Integer.MAX_VALUE;
        }
        return interval;
    }

    private LocalDateTime timePut() {
        String date = "";
        LocalDateTime time = null;
        try {
            date = reader.readLine();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            time = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999);
        }
        return time;
    }

}
