//package ua.edu.sumdu.j2se.mayfet.tasks.controller;
//
//import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
//import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;
//import ua.edu.sumdu.j2se.mayfet.tasks.view.NotificationView;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//public class NotificationController extends Thread {
//    private NotificationView view;
//    private AbstractTaskList taskList;
//
//    public NotificationController(NotificationView view, AbstractTaskList taskList) {
//        this.view = view;
//        this.taskList = taskList;
//    }
//
//    @Override
//    public void run() {
//        LocalDateTime next, now;
//        while (true) {
//            if (taskList != null || taskList.getTask(0) != null) {
//                now = LocalDateTime.now().withSecond(0).withNano(0);
//                for (Task task : taskList) {
//                    //System.out.println(taskList.getTask(i).nextTimeAfter(taskList.getTask(i).getStartTime()));
//                    //System.out.println("print");
//                    if (taskList.size() > 0) {
//                        if (task.getTime().isEqual(LocalDateTime.now())) {
//                            view.printInfo();
//                            try {
//                                Thread.sleep(Duration.between(LocalDateTime.now(), now.plusMinutes(1)).toMillis());
//                            } catch (InterruptedException e) {
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
