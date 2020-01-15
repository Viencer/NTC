package ua.edu.sumdu.j2se.mayfet.tasks;

import ua.edu.sumdu.j2se.mayfet.tasks.controller.Controller;
import ua.edu.sumdu.j2se.mayfet.tasks.controller.MainController;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Tasks;
import ua.edu.sumdu.j2se.mayfet.tasks.view.MainView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Manager started");
        AbstractTaskList taskList = new ArrayTaskList();
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        mainController.process(null);
        System.out.println("Manager was closed");
//        ArrayTaskList taskList = new ArrayTaskList();
//        String Start = "2020-04-23 12:30";
//        String End = "2020-04-26 12:30";
//        DateTimeFormatter formatte = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime S = LocalDateTime.parse(Start, formatte);
//        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime E = LocalDateTime.parse(End, formatt);
//        Task task1 = new Task("2", S, E, 30000);
//        Task task = new Task("1", S);
//        task.setActive(true);
//        task1.setActive(true);
//        //taskList.add(task);
//        taskList.add(task1);
//        System.out.println(Tasks.calendar(taskList, S, E));
    }


}
