package ua.edu.sumdu.j2se.mayfet.tasks.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Task implements Serializable, Cloneable {
    private String title;
    private boolean active;
    private LocalDateTime time;
    private int repeatInterval;
    private boolean repeated;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * первый конструктор
     */
    public Task(String title, LocalDateTime time) {
        setTitle(title);
        setTime(time);
        if (time == null) {
            throw new IllegalArgumentException("time is minus");
        }
    }

    /**
     * второй конструктор
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
        setTime(start, end, interval);
        if (start == null || end == null) {
            throw new IllegalArgumentException("time is wrong");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (repeated) {
            return startTime;
        }
        return time;
    }

    public void setTime(LocalDateTime time) {
        if (repeated) {
            repeated = false;
        }
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (!repeated) {
            return time;
        }
        return startTime;
    }

    public LocalDateTime getEndTime() {
        if (!repeated) {
            return time;
        }
        return endTime;
    }

    public int getRepeatInterval() {
        if (!repeated) {
            return 0;
        }
        return repeatInterval;
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (!repeated) {
            repeated = true;
        }
    }

    public boolean isRepeated() {
        return repeated;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (isActive()) {
            if (getStartTime().isAfter(current)) {//>
                return getStartTime();
            } else if ((getEndTime().isBefore(current)) || (this.startTime == null)) {//<=
                return null;
            }
            if (getRepeatInterval() > 0) {
                if (getStartTime().isAfter(getEndTime())) {
                    return null;
                }
                if ((current.isAfter(getStartTime()) || current.isEqual(getStartTime()))) {//>=
                    LocalDateTime a = getStartTime();
                    if (current.isEqual(getEndTime())) {
                        return null;
                    }
                    while ((a.isBefore(getEndTime()))) {
                        a = a.plusSeconds(getRepeatInterval());
                        if (a.isAfter(getEndTime())) {
                            return null;
                        }
                        if (current.isBefore(a)) {
                            return a;
                        }
                    }
                    if (current.isAfter(endTime)) {
                        return null;
                    }
                }
            }
        } else if (!isActive()) {
            return null;
        }
        return current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isActive() == task.isActive() &&
                getTime() == task.getTime() &&
                getRepeatInterval() == task.getRepeatInterval() &&
                isRepeated() == task.isRepeated() &&
                getStartTime() == task.getStartTime() &&
                getEndTime() == task.getEndTime() &&
                Objects.equals(getTitle(), task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), getTime(), getRepeatInterval(), isRepeated(), getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", active=" + active +
                ", time=" + time +
                ", repeatInterval=" + repeatInterval +
                ", repeated=" + repeated +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Task clone() {
        try {
            Task task = (Task) super.clone();
            return task;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
