package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.ChooseNum;
import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class SaveLoadTasksView implements View, TaskAction {
    int check;

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (check == ChooseNum.FIRST) {
            System.out.println("Задания были сохранены");
            return Controller.FINISH_ACTION;
        } else {
            System.out.println("Задания были загружены");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {
        System.out.println("введите тип действия");
        System.out.println("1 - сохранить и выйти,  2 - загрузить,  3 - назад в меню");
        int action = 0;
        try {
            action = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -1;
        }
        this.check = action;
        return action;
    }

    public String fileName() {
        String nameFile = "";
        try {
            System.out.println("введите имя файла сохранения");
            nameFile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFile;
    }
}
