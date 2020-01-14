package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.ChooseNum;
import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class SaveLoadTasksView implements View {
    int check;

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (check == ChooseNum.FIRST) {
            System.out.println("Tasks was saved");
        } else {
            System.out.println("Tasks was loaded");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    public int saveOrLoad() {
        System.out.println("Put action type");
        System.out.println("1 - save");
        System.out.println("2 - load");
        int action = 0;
        try {
            String indexIn = reader.readLine();
            action = Integer.parseInt(indexIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.check = action;
        return action;
    }

    public String fileName() {
        String nameFile = "";
        try {
            nameFile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameFile;
    }
}
