package ua.edu.sumdu.j2se.mayfet.tasks.model;

import com.google.gson.Gson;
import ua.edu.sumdu.j2se.mayfet.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.mayfet.tasks.model.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outs = new DataOutputStream(out);
        try {
            outs.writeInt(tasks.size());
            for (int i = 0; i < tasks.size(); i++) {
                outs.writeUTF(tasks.getTask(i).getTitle());
                outs.writeInt(tasks.getTask(i).getTime().getNano());
                outs.writeInt(tasks.getTask(i).getStartTime().getNano());
                outs.writeInt(tasks.getTask(i).getEndTime().getNano());
                outs.writeInt(tasks.getTask(i).getRepeatInterval());
                outs.writeBoolean(tasks.getTask(i).isActive());
                outs.writeBoolean(tasks.getTask(i).isRepeated());
            }
        } finally {
            outs.close();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream ins = new DataInputStream(in);
        try {
            int count = ins.readInt();
            for (int i = 0; i < count; i++) {
                String title = ins.readUTF();
                LocalDateTime time = LocalDateTime.ofEpochSecond(ins.readInt(), 0, ZoneOffset.UTC);//СОЗДАНИЕ СНАЧАЛА ЭПОХИ
                LocalDateTime from = LocalDateTime.ofEpochSecond(ins.readInt(), 0, ZoneOffset.UTC);
                LocalDateTime to = LocalDateTime.ofEpochSecond(ins.readInt(), 0, ZoneOffset.UTC);
                int repeatInterval = ins.readInt();
                boolean active = ins.readBoolean();
                boolean repeated = ins.readBoolean();
                if (repeated) {
                    Task task = new Task(title, from, to, repeatInterval);
                    task.setActive(active);
                    tasks.add(task);
                } else {
                    Task task = new Task(title, time);
                    task.setActive(active);
                    tasks.add(task);
                }
            }
        } finally {
            ins.close();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        try {
            write(tasks, out);
        } finally {
            out.close();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        try {
            read(tasks, in);
        } finally {
            in.close();
        }
    }


    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson write = new Gson();
        write.toJson(tasks, out);
        out.flush();// буфер
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        Gson read = new Gson();
        AbstractTaskList task;
        task = read.fromJson(in, tasks.getClass());
        for (Task ta : task) {
            tasks.add(ta);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        try {
            write(tasks, out);
        } finally {
            out.close();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        try {
            read(tasks, in);
        } finally {
            in.close();
        }
    }
}
