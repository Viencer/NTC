package ua.edu.sumdu.j2se.mayfet.tasks;

import org.jetbrains.annotations.NotNull;

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
        TreeMap<LocalDateTime, Set<Task>> sort = new TreeMap<LocalDateTime, Set<Task>>();
        for (Task i : tasks) {
            LocalDateTime timeNow = i.nextTimeAfter(start);
            while ((!timeNow.isAfter(end) || timeNow.isEqual(end)) && (timeNow.isAfter(start) || timeNow.isEqual(start))) {
                if (sort.containsKey(timeNow)) {
                    sort.get(timeNow).add(i);
                } else if (!sort.containsKey(timeNow)) {
                    HashSet<Task> set = new HashSet<Task>();
                    set.add(i);
                    sort.put(timeNow, set);
                } else {
                    return null;
                }
                timeNow = i.nextTimeAfter(timeNow);
            }
        }
        return sort;
    }
}
