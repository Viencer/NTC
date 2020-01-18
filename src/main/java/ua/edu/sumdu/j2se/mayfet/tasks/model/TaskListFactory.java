package ua.edu.sumdu.j2se.mayfet.tasks.model;

import java.io.Serializable;

public class TaskListFactory implements Serializable {
   public static AbstractTaskList createTaskList(ListTypes.types type){
        if (type.equals(ListTypes.types.ARRAY)){
              return new ArrayTaskList();
        }
        if (type.equals(ListTypes.types.LINKED)){
             return new LinkedTaskList();
        }
        return null;
    }
}
