package ua.edu.sumdu.j2se.mayfet.tasks.model;

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
        for (Task i : tasks) {
            if (i.isActive()) {
                LocalDateTime timeNow = i.nextTimeAfter(start.minusNanos(1));
                while (timeNow != null && !timeNow.isAfter(end)) {
                    if (sort.containsKey(timeNow)) {
                        sort.get(timeNow).add(i);
                    } else if (!sort.containsKey(timeNow)) {
                        Set<Task> set = new HashSet<>();
                            set.add(i);
                            sort.put(timeNow, set);
                    }
                    timeNow = i.nextTimeAfter(timeNow);
                }
            }
        }
        return sort;
    }
}
