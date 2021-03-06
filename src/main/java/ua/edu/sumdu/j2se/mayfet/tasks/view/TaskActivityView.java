package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class TaskActivityView implements View, TaskAction {
    @Override
    public int taskChoose() {
        System.out.println("Выберите действие");
        System.out.println("1 - установить активность задачи,  2 - назад в меню");
        int taskType = 0;
        try {
            taskType = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return taskType;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Настройки активности закрыты");
        return Controller.MAIN_MENU_ACTION;
    }

    public int index() {
        int index = 0;
        try {
            System.out.print("введите id задачи ");
            index = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
        return index;
    }

    public int activityMode() {
        int mode = 0;
        try {
            System.out.println("Установить режим");
            System.out.println("1 - активный режим");
            System.out.println("2 - режим ожидания");
            mode = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        return mode;
    }
}
