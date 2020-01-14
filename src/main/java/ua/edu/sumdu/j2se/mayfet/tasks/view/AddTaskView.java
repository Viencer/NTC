package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddTaskView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("New task was added");
        return Controller.MAIN_MENU_ACTION;
    }

    public int taskChoose() {
        System.out.println("Put task type");
        System.out.println("1 - non repeatable");
        System.out.println("2 - repeatable");
        int taskType = 0;
        try {
            String indexIn = reader.readLine();
            taskType = Integer.parseInt(indexIn);
        } catch (IOException e) {
            e.printStackTrace();
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
        String date = "";
        try {
            date = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(date, formatter);
        return time;
    }

    public LocalDateTime timeTaskStart() {
        System.out.println("Put start date (example: 2020-04-22 12:30)");
        String date = "";
        try {
            date = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(date, formatter);
        return start;
    }

    public LocalDateTime timeTaskEnd() {
        System.out.println("Put end date (example: 2020-04-22 12:30)");
        String date = "";
        try {
            date = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(date, formatter);
        return start;
    }

    public int repeatInterval() {
        System.out.println("Put interval");
        int interval = 0;
        try {
            String indexIn = reader.readLine();
            interval = Integer.parseInt(indexIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interval;
    }

}
