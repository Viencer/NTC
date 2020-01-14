package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class AddTaskView implements View, TaskAction {
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
            return 3;
        }
        return taskType;
    }

    public String nameTask() {
        System.out.println("Put your name");
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public LocalDateTime timeTask() {
        System.out.println("Put date (example: 2020-04-08 12:30)");
        LocalDateTime time;
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
            return time = LocalDateTime.now();
        }
        return time;
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
            return start = LocalDateTime.now();
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
            return end = LocalDateTime.now();
        }
        return end;
    }

    public int repeatInterval() {
        System.out.println("Put interval");
        int interval = 0;
        try {
            String indexIn = reader.readLine();
            interval = Integer.parseInt(indexIn);
        } catch (IOException | NumberFormatException e) {
            interval = 0;
        }
        return interval;
    }

}
