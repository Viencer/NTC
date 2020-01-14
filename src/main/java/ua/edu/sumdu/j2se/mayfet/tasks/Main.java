package ua.edu.sumdu.j2se.mayfet.tasks;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.controller.MainController;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.view.MainView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Manager started");
        AbstractTaskList taskList = new ArrayTaskList();
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        mainController.process(null);
        System.out.println("Manager was closed");
    }


}
