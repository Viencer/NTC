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
        LocalDateTime endTime = timeTaskEnd();
        if ((startTime.isEqual(LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999)))) {
            System.out.println("ERROR UNEXPECTED TIME");
            return Controller.CALENDAR_ACTION;
        }
        if ((endTime.isBefore(LocalDateTime.now()))) {
            System.out.println("ERROR UNEXPECTED END TIME");
            return Controller.CALENDAR_ACTION;
        }
        System.out.println("repeated tasks: ");
        SortedMap<LocalDateTime, Set<Task>> calendarView = Tasks.calendar(taskList, startTime, endTime);
        for (SortedMap.Entry<LocalDateTime, Set<Task>> element : calendarView.entrySet()) {
            for (Task task : element.getValue()) {
                System.out.print("title = " + task.getTitle() + "-->");
            }
            System.out.println(element.getKey() + "\n");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {
        System.out.println("Put task type");
        System.out.println("1 - check action date,  2 - back to menu");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public LocalDateTime timeTaskStart() {
        System.out.print("Put start date (example: 2020-04-22 12:30) = ");
        String date = "";
        LocalDateTime start;
        try {
            date = reader.readLine();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            start = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999);
        }
        return start;
    }

    public LocalDateTime timeTaskEnd() {
        System.out.print("Put end date (example: 2020-04-22 12:30) = ");
        String date = "";
        LocalDateTime end;
        try {
            date = reader.readLine();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            end = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.now().minusYears(999);
        }
        return end;
    }
}
