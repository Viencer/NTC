package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Tasks;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarView implements View, TaskAction {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("calendar view");
        LocalDateTime timeStart = timeTaskStart();
        LocalDateTime timeEnd = timeTaskEnd();
        if ((timeStart.isEqual(LocalDateTime.ofEpochSecond(1, 1, ZoneOffset.UTC).minusYears(999)))) {
            System.out.println("ERROR UNEXPECTED TIME");
            return 0;
        }
        if ((timeEnd.isBefore(LocalDateTime.now()))) {
            System.out.println("ERROR UNEXPECTED END TIME");
            return 0;
        }
        System.out.println("non repeated: ");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).getRepeatInterval() == 0) {
                System.out.print(i + ". {" + taskList.getTask(i).getTime() + "=[ " + taskList.getTask(i) + "}], ");
            }

        }
        System.out.println("");
        System.out.println("repeated: ");
        Tasks.calendar(taskList, timeStart, timeEnd).forEach((localDateTime, tasks1) ->
                System.out.println(localDateTime + " = " + taskList.getTask(0)));
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {
        System.out.println("Put task type");
        System.out.println("1 - check action date");
        System.out.println("2 - back to menu");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 2;
        }
        return taskType;
    }

    public LocalDateTime timeTaskStart() {
        System.out.println("Put start date (example: 2020-04-22 12:30)");
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
        System.out.println("Put end date (example: 2020-04-22 12:30)");
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
