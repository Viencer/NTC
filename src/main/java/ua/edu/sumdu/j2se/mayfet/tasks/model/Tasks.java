package ua.edu.sumdu.j2se.mayfet.tasks.model;

import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                return StreamSupport.stream(tasks.spliterator(), false).filter(task ->
                        task != null && task.isActive() && task.nextTimeAfter(start) != null
                                && (end.isAfter(task.nextTimeAfter(start))
                                || end.isEqual(task.nextTimeAfter(start)))).iterator();
            }
        };
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> sort = new TreeMap<>();
        for (Task task : tasks) {
            LocalDateTime timeNow = task.nextTimeAfter(start.minusNanos(1));
            while (timeNow != null && !timeNow.isAfter(end)) {
                if (sort.containsKey(timeNow)) {
                    sort.get(timeNow).add(task);
                } else if (!sort.containsKey(timeNow)) {
                    Set<Task> set = new HashSet<>();
                    set.add(task);
                    sort.put(timeNow, set);
                }
                timeNow = task.nextTimeAfter(timeNow);
            }
        }
        return sort;
    }
}
