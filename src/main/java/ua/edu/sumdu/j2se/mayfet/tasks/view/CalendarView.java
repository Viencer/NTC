package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalendarView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("calendar view");
        return Controller.MAIN_MENU_ACTION;
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
            return LocalDateTime.now().minusYears(999);
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
