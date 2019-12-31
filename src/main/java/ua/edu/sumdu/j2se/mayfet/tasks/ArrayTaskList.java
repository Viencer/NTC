package ua.edu.sumdu.j2se.mayfet.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ArrayTaskList extends AbstractTaskList implements Cloneable, Iterable<Task> {
    private Task[] tasks = new Task[0];

    public void add(Task task) {
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = task;
                i++;
            }
        }
    }

    public boolean remove(Task task) {
        for (int i = 0; i < tasks.length; i++) {
            if (task.hashCode() == tasks[i].hashCode()) {
                System.arraycopy(tasks, i + 1, tasks, i, tasks.length - 1 - i);
                tasks = Arrays.copyOf(tasks, tasks.length - 1);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return tasks.length;
    }

    public Task getTask(int index) {
        if (index > tasks.length) {
            throw new IndexOutOfBoundsException("your index is bigger than array length");
        }
        return tasks[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList tasks1 = (ArrayTaskList) o;
        return Arrays.equals(tasks, tasks1.tasks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tasks);
    }

    public ArrayTaskList clone() {
        try {
            ArrayTaskList array = (ArrayTaskList) super.clone();
            array.tasks = Arrays.copyOf(tasks, tasks.length);
            return array;
        } catch (CloneNotSupportedException ex) {
            System.out.println("CloneNotSupportedException");
            return null;
        }
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < size();
            }

            @Override
            public Task next() {
                return getTask(current++);
            }

            @Override
            public void remove() throws IllegalStateException {
                ArrayTaskList arrayrem = new ArrayTaskList();
                if (current == 0) {
                    throw new IllegalStateException();
                } else {
                    arrayrem.tasks = tasks;
                    Task tasksVoid = getTask(--current);
                    arrayrem.remove(tasksVoid);
                    tasks = Arrays.copyOf(tasks, tasks.length - 1);
                }
            }
        };
        return it;
    }

    @Override
    public Stream<Task> getStream() {
        return Stream.of(this.tasks);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                '}';
    }
}