package ua.edu.sumdu.j2se.mayfet.tasks.model;

import java.util.*;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Iterable<Task> {
    private Node head;
    private Node end;
    private int cout;

    public void add(Task task) {
        if (isEmpty()) {
            head = new Node(task, null);
            end = head;
        } else {
            end.setNext(new Node(task, null));
            end = end.getNext();
        }
        cout++;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean remove(Task task) {
        Node curr = head;
        Node prev = null;
        for (int i = 0; i < cout; i++) {
            if (getTask(i).equals(task)) {
                if (prev != null) {
                    prev.next = curr.next;
                    if (curr.next == null)
                        end = prev;
                } else {
                    head = head.next;
                }
                cout--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }


    public int size() {
        return this.cout;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size() - 1) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
        return end.getData();
    }

    public static class Node {
        public Task data;
        public Node next;

        public Node(Task data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Task getData() {
            return this.data;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedTaskList) {
            LinkedTaskList listOther = (LinkedTaskList) o;
            if (this.size() == listOther.size()) {
                Iterator list1 = this.iterator();
                Iterator list2 = listOther.iterator();

                while (list1.hasNext()) {
                    Object e1 = list1.next();
                    Object e2 = list2.next();

                    if (!(e1 == null ? e2 == null : e1.equals(e2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Task task : this) {
            hashCode = 31 * hashCode + (task == null ? 0 : task.hashCode());
        }
        return hashCode;
    }

    public LinkedTaskList clone() {
        try {
            LinkedTaskList link = (LinkedTaskList) super.clone();
            link.cout = 0;
            for (int i = 0; i < cout; i++) {
                link.add(getTask(i));
            }
            return link;
        } catch (CloneNotSupportedException ex) {
            System.out.println("CloneNotSupportedException");
            return null;
        }
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {
            Node current = head;
            Node prev = null;
            Node prev2 = null;
            boolean removeCall = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Task next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Task tas = current.getData();
                prev2 = prev;
                prev = current;
                current = current.getNext();
                removeCall = false;
                return tas;
            }

            @Override
            public void remove() {
                if (prev == null || removeCall) {
                    throw new IllegalStateException();
                }
                if (prev2 == null) {
                    head = current;
                } else {
                    prev2.setNext(current);
                }
                cout--;
                removeCall = true;
            }
        };
        return it;
    }

    @Override
    public Stream<Task> getStream() {
        ArrayTaskList list = new ArrayTaskList();
        for (int i = 0; i < size(); i++) {
            list.add(getTask(i));
        }
        return list.getStream();
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "head=" + head +
                ", end=" + end +
                ", cout=" + cout +
                '}';
    }
}
