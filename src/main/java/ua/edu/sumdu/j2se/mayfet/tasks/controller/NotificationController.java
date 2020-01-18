package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Tasks;
import ua.edu.sumdu.j2se.mayfet.tasks.view.NotificationView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class NotificationController extends Thread {
    private static final Logger logger = Logger.getLogger(NotificationController.class);
    NotificationView view;
    private AbstractTaskList taskList;

    public NotificationController(NotificationView view, AbstractTaskList taskList) {
        this.view = view;
        this.taskList = taskList;
    }

    @Override
    public void run() {
        while (true) {
            LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
            LocalDateTime endTime = LocalDateTime.now().withSecond(0).withNano(0).plusYears(200);
            String title = "";
            SortedMap<LocalDateTime, Set<Task>> calendarView = Tasks.calendar(taskList, now, endTime); // Вывод времени действия
            for (SortedMap.Entry<LocalDateTime, Set<Task>> element : calendarView.entrySet()) {
                for (Task task : element.getValue()) {
                    title = task.getTitle();
                    if (element.getKey().isEqual(now)) {
                        view.printInfo(element.getKey(), title);
                    }

                }
            }
            try {
                Thread.sleep(Duration.between(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1)).toMillis()); // ожидание
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}
