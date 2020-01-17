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
        System.out.println("1 - change task parameter,  2 - main menu ");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public int taskChooseNon() {
        System.out.println("1 - change name,  2 - change time,  3 - back");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public int taskChooseRep() {
        System.out.println("1 - change name,  2 - change time,  3 - change interval,  4-back");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    public int index() {
        System.out.print("Put task index to change: ");
        int index = 0;
        try {
            String indexIn = reader.readLine();
            index = Integer.parseInt(indexIn);
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
        return index;
    }

    public int interval() {
        System.out.print("Put new interval: ");
        int interval = 0;
        try {
            String inter = reader.readLine();
            interval = Integer.parseInt(inter);
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
        return interval;
    }

    public LocalDateTime time() {
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

    public LocalDateTime startTime() {
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

    public LocalDateTime endTime() {
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

    public String titleNew() {
        System.out.print("Put your name = ");
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        return Controller.TASK_CHANGE;
    }
}
