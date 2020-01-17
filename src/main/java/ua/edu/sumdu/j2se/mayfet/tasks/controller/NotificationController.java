package ua.edu.sumdu.j2se.mayfet.tasks.controller;

import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
import ua.edu.sumdu.j2se.mayfet.tasks.view.NotificationView;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotificationController extends Thread {
    LocalDateTime bug = LocalDateTime.now().minusYears(99).withSecond(0).withNano(0);
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
            if (taskList.size() > 0) {
                for (Task i : taskList) {
                    if (i.isActive()) {
                        LocalDateTime taskTime = i.nextTimeAfter(now.minusMinutes(1).withSecond(0).withNano(0));
                        if (taskTime != null) {
                            if (!taskTime.isEqual(bug)) {
                                if (taskTime.isEqual(now)) {
                                    bug = taskTime;
                                    view.printInfo(taskTime, i.getTitle());
                                }
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(Duration.between(LocalDateTime.now(), now.plusMinutes(1)).toMillis()); // ожидание
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
