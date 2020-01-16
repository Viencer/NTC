package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTaskView implements View, TaskAction {
    int nmm;

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("New task was added");
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {
        System.out.println("Put task type");
        System.out.println("1 - non repeatable");
        System.out.println("2 - repeatable");
        System.out.println("3 - back to menu");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public String nameTask() {
        System.out.print("Put your name = ");
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public LocalDateTime timeTask() {
        System.out.print("Put date (example: 2020-04-08 12:30) = ");
        LocalDateTime time = null;
        String date = "";
        try {
            date = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            time = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.now().minusYears(999);
        }
        return time;
    }

    public LocalDateTime timeTaskStart() {
        System.out.print("Put start date (example: 2020-04-22 12:30) = ");
        String date = "";
        LocalDateTime start = null;
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
        LocalDateTime end = null;
        try {
            date = reader.readLine();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            end = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return end = LocalDateTime.now().minusSeconds(1);
        }
        return end;
    }

    public int repeatInterval() {
        System.out.print("Put interval = ");
        int interval = 0;
        try {
            String indexIn = reader.readLine();
            interval = Integer.parseInt(indexIn);
        } catch (IOException | NumberFormatException e) {
            interval = Integer.MAX_VALUE;
        }
        return interval;
    }

}
