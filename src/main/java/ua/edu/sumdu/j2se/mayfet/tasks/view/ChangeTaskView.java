package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ChangeTaskView implements View, TaskAction {

    @Override
    public int taskChoose() {
        System.out.println("1 - изменить параметр задачи,  2 - выход в меню ");
        return taskChooseAll();
    }

    public int taskChooseNon() {
        System.out.println("1 - изменить имя,  2 - изменить время,  3 - назад");
        return taskChooseAll();
    }

    public int taskChooseRep() {
        System.out.println("1 - изменить имя,  2 - изменить время,  3 - изменить интервал,  4 - назад");
        return taskChooseAll();
    }

    public int index() {
        System.out.print("введите id задачи: ");
        return numCode();
    }

    public int interval() {
        System.out.print("введите новый интервал: ");
        return numCode();
    }

    public LocalDateTime time() {
        System.out.print("введите дату (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public LocalDateTime startTime() {
        System.out.print("введите дату начала (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public LocalDateTime endTime() {
        System.out.print("введите дату окончания (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public String titleNew() {
        System.out.print("введите новое имя ");
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
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

    private int taskChooseAll() {
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    private int numCode() {
        int num = 0;
        try {
            String indexIn = reader.readLine();
            num = Integer.parseInt(indexIn);
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
        return num;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        return Controller.TASK_CHANGE;
    }
}
