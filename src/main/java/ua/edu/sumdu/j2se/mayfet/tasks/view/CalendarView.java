package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Tasks;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView implements View, TaskAction {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        LocalDateTime startTime = timeTaskStart();
        LocalDateTime endTime = timeTaskEnd();   // ловим ошибки
        if ((startTime.isEqual(LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999)))) {
            System.out.println("ОШИБКА НЕОЖИДАННОЕ ВРЕМЯ");
            return Controller.CALENDAR_ACTION;
        }
        if ((endTime.isBefore(LocalDateTime.now()))) {          // ловим ошибки
            System.out.println("ОШИБКА НЕОЖИДАННОЕ ВРЕМЯ ОКОНЧАНИЯ");
            return Controller.CALENDAR_ACTION;
        }
        SortedMap<LocalDateTime, Set<Task>> calendarView = Tasks.calendar(taskList, startTime, endTime); // Вывод времени действия
        for (SortedMap.Entry<LocalDateTime, Set<Task>> element : calendarView.entrySet()) {
            for (Task task : element.getValue()) {
                System.out.print("title = " + task.getTitle() + "-->");
            }
            System.out.println(element.getKey() + "\n");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {                               //выбор действия
        System.out.println("введите тип задачи");
        System.out.println("1 - проверить активность задач,  2 - выход в меню");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public LocalDateTime timeTaskStart() {                            //ввод времени начала
        System.out.print("введите дату начала (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");
        return timePut();
    }

    public LocalDateTime timeTaskEnd() {
        System.out.print("введите дату окончания (пример ГГ-ММ-ДД ЧЧ:ММ = 2020-04-08 12:30) = ");//ввод времени конца
        return timePut();
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
