package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View {


    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("choose activity");
        System.out.println("1 check tasks");
        System.out.println("2 add new task");
        System.out.println("3 remove task");
        System.out.println("4 choose time interval");
        System.out.println("5 exit");
        int variant = 0;
        try {
            variant = Integer.parseInt(reader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
        return variant;
    }
}
