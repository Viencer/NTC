package ua.edu.sumdu.j2se.mayfet.tasks.view;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;

import java.io.IOException;

public class MainView implements View {


    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Выберите действие");
        System.out.println("1 Журнал заданий");
        System.out.println("2 добавить новую задачу");
        System.out.println("3 удалить задачу");
        System.out.println("4 календарь");
        System.out.println("5 сохранить/загрузить задачи");
        System.out.println("6 изменить активность");
        System.out.println("7 изменить задачу");
        System.out.println("8 выход");
        int variant = 0;
        try {
            variant = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
        return variant;
    }
}
