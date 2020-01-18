package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;

import java.time.LocalDateTime;

public class NotificationView {
    public int printInfo(LocalDateTime localDateTime, String string) {
        System.out.println("\n#############################################\n");
        System.out.println("\nЗадание " + string + " должно быть выполнено. Время: " + localDateTime + "\n");
        System.out.println("\n#############################################\n");
        return Controller.MAIN_MENU_ACTION;
    }
}
