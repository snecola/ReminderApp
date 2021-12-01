package edu.qc.seclass.rlm;

public class Reminder {

    private Integer reminderId;
    private Integer listId;
    private String reminderName;
    private String reminderLocation;
    private String reminderDescription;
    private String reminderTime;
    private String reminderDate;
    private Integer reminderAlert;
    private Integer reminderPriority;
    private Integer checkedOff;

    /*
    * Constructor
    */
    public Reminder(Integer reminderId, Integer listId, String reminderName,
                    String reminderLocation, String reminderDescription,
                    String reminderTime, String reminderDate, Integer reminderAlert,
                    Integer reminderPriority, Integer checkedOff) {
        this.reminderId = reminderId;
        this.listId = listId;
        this.reminderName = reminderName;
        this.reminderLocation = reminderLocation;
        this.reminderDescription = reminderDescription;
        this.reminderTime = reminderTime;
        this.reminderDate = reminderDate;
        this.reminderAlert = reminderAlert;
        this.reminderPriority = reminderPriority;
        this.checkedOff = checkedOff;
    }

    /*
    * Getters and Setters
    */
    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getReminderName() {
        return reminderName;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getReminderLocation() {
        return reminderLocation;
    }

    public void setReminderLocation(String reminderLocation) {
        this.reminderLocation = reminderLocation;
    }

    public String getReminderDescription() {
        return reminderDescription;
    }

    public void setReminderDescription(String reminderDescription) {
        this.reminderDescription = reminderDescription;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public Integer getReminderAlert() {
        return reminderAlert;
    }

    public void setReminderAlert(Integer reminderAlert) {
        this.reminderAlert = reminderAlert;
    }

    public Integer getReminderPriority() {
        return reminderPriority;
    }

    public void setReminderPriority(Integer reminderPriority) {
        this.reminderPriority = reminderPriority;
    }

    public Integer getCheckedOff() {
        return checkedOff;
    }

    public void setCheckedOff(Integer checkedOff) {
        this.checkedOff = checkedOff;
    }
    //toString
    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", listId=" + listId +
                ", reminderName='" + reminderName + '\'' +
                ", reminderLocation='" + reminderLocation + '\'' +
                ", reminderDescription='" + reminderDescription + '\'' +
                ", reminderTime='" + reminderTime + '\'' +
                ", reminderDate='" + reminderDate + '\'' +
                ", reminderAlert=" + reminderAlert +
                ", reminderPriority=" + reminderPriority +
                ", checkedOff=" + checkedOff +
                '}';
    }
}
