package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface View {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public int printInfo(AbstractTaskList taskList);
}
