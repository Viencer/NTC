package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.mayfet.tasks.view.SaveLoadTasksView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoadTasksController extends Controller {

    public SaveLoadTasksController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((SaveLoadTasksView) view).saveOrLoad();
        File directory = new File("saves");
        directory.mkdir();
        if (taskChoose == ChooseNum.FIRST) {
            try {
                String nameFile = ((SaveLoadTasksView) view).fileName();
                TaskIO.write(taskList, new FileWriter(new File("saves/" + nameFile + ".json")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (taskChoose == ChooseNum.SECOND) {
            try {
                String nameFile = ((SaveLoadTasksView) view).fileName();
                TaskIO.read(taskList, new FileReader("saves/" + nameFile + ".json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR YOU CHOOSE WRONG NUMBER");
            return Controller.MAIN_MENU_ACTION;
        }
        return view.printInfo(taskList);
    }
}
