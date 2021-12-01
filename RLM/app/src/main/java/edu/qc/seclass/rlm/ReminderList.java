package edu.qc.seclass.rlm;

import java.util.ArrayList;

public class ReminderList {

    private String listId;
    private String listName;
    private String listColor;
    private String listPriority;
    //Query for all Reminders belonging to this.listId to obtain reminderList
    private ArrayList<Reminder> reminderList;

    // Constructor For Reminder Lists
    public ReminderList(String lId, String lName, String lColor, String lPriority, ArrayList<Reminder> rList){
        this.listId = lId;
        this.listName = lName;
        this.listColor = lColor;
        this.listPriority = lPriority;
        this.reminderList = rList;
    }
    //Getters and Setters
    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListColor() {
        return listColor;
    }

    public void setListColor(String listColor) {
        this.listColor = listColor;
    }

    public ArrayList<Reminder> getReminderList() {
        return reminderList;
    }

    public void setReminderList(ArrayList<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

    public String getListPriority() {
        return listPriority;
    }

    public void setListPriority(String listPriority) {
        this.listPriority = listPriority;
    }
    //toString method
    @Override
    public String toString() {
        return "ReminderList{" +
                "listId='" + listId + '\'' +
                ", listName='" + listName + '\'' +
                ", listColor='" + listColor + '\'' +
                ", listPriority=" + listPriority +
                ", reminderList=" + reminderList +
                '}';
    }


}
