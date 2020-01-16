package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;

import java.time.LocalDateTime;

public class NotificationView {
    public int printInfo(LocalDateTime localDateTime, String string) {
        System.out.println("NOTIFICATION");
        System.out.println("Task " + string + " must be done. Current time is: " + localDateTime);
        return Controller.MAIN_MENU_ACTION;
    }
}
