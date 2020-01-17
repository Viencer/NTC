package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.mayfet.tasks.view.SaveLoadTasksView;
import ua.edu.sumdu.j2se.mayfet.tasks.view.View;

import java.io.*;

public class SaveLoadTasksController extends Controller {

    public SaveLoadTasksController(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        int taskChoose = ((SaveLoadTasksView) view).taskChoose();
        File directory = new File("saves");  // создаём директорию сохранений
        directory.mkdir();
        if (taskChoose == ChooseNum.FIRST) { // если выбрали 1, то сохраняем и выодим
            try {
                String nameFile = ((SaveLoadTasksView) view).fileName(); // можно вводить имя файла сохранения
                TaskIO.write(taskList, new FileWriter(new File("saves/" + nameFile + ".json"))); // сохранение в формате json
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (taskChoose == ChooseNum.SECOND) { // если выбрали 2, то загружаем сохранение
            try {
                String nameFile = ((SaveLoadTasksView) view).fileName();   // ввод название файла для загрузки
                TaskIO.read(taskList, new FileReader("saves/" + nameFile + ".json"));
            } catch (IOException e) {
                System.out.println(Errors.ERROR5);
                return Controller.SAVE_LOAD_TASKS;
            }
        } else if (taskChoose == ChooseNum.THIRD) {  // выход в главное меню
            return Controller.MAIN_MENU_ACTION;
        } else {
            System.out.println(Errors.ERROR4);
            return Controller.SAVE_LOAD_TASKS;
        }
        return view.printInfo(taskList);
    }
}
