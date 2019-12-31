package ua.edu.sumdu.j2se.mayfet.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        if (this.getClass().equals(LinkedTaskList.class)) {
            LinkedTaskList tas1 = new LinkedTaskList();
            this.getStream().filter(task -> task != null &&
                    task.nextTimeAfter(from) != null && task.nextTimeAfter(from).isBefore(to)).forEach(tas1::add);
            return tas1;
        } else if (this.getClass().equals(ArrayTaskList.class)) {
            ArrayTaskList tas2 = new ArrayTaskList();
            this.getStream().filter(task -> task != null &&
                    task.nextTimeAfter(from) != null && task.nextTimeAfter(from).isBefore(to)).forEach(tas2::add);
            return tas2;
        }
        return null;
    }

    public abstract Stream<Task> getStream();
}
