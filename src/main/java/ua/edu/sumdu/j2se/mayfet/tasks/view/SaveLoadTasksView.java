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
            System.out.println("Tasks was saved");
            return Controller.FINISH_ACTION;
        } else {
            System.out.println("Tasks was loaded");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public int taskChoose() {
        System.out.println("Put action type");
        System.out.println("1 - save and exit");
        System.out.println("2 - load");
        System.out.println("3 - back to menu");
        int action = 0;
        try {
            action = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 3;
        }
        this.check = action;
        return action;
    }

    public String fileName() {
        String nameFile = "";
        try {
            System.out.println("Put save file name");
            nameFile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFile;
    }
}
