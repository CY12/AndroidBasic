package com.example.space.priority;

import java.util.ArrayList;
import java.util.List;

public class NoticePriority {

    private int priority;

    private Object data;

    private List<NoticePriority> priorityList = new ArrayList<>();

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void insertNotice(NoticePriority data){
        if (priorityList.isEmpty()){
            priorityList.add(data);
        }

        for (int i = priorityList.size(); i > 0; i--) {
            if (priorityList.get(i).getPriority() < data.getPriority()){
                priorityList.add(i,data);
                return;
            }
        }

    }


}
