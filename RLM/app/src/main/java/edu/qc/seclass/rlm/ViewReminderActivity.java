package edu.qc.seclass.rlm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewReminderActivity extends AppCompatActivity {

    private DatabaseHelper db;
    ArrayList<Reminder> queryResponse;
    Reminder reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);

        Intent i = getIntent();
        String reminderId = i.getStringExtra("ReminderId");
        System.out.println(reminderId);

        db = new DatabaseHelper(this);

        queryResponse = db.getReminderByID(Integer.parseInt(reminderId));
        reminder = queryResponse.get(0);

        populateReminderInfo();
    }

    private void populateReminderInfo () {
        TextView reminderName = findViewById(R.id.reminderNameView);
        TextView reminderDesc = findViewById(R.id.reminderDescriptionView);
        TextView reminderDate = findViewById(R.id.reminderDateView);
        TextView reminderTime = findViewById(R.id.reminderTimeView);
        TextView reminderLocation = findViewById(R.id.reminderLocationView);
        TextView reminderPriorty = findViewById(R.id.reminderPriorityView);
        CheckBox reminderCheckBox = findViewById( R.id.reminderCheckBox);
        CheckBox reminderAlertBox = findViewById(R.id.reminderAlertBox);

        reminderName.setText(reminder.getReminderName());
        reminderDesc.setText(reminder.getReminderDescription());
        reminderDate.setText(reminder.getReminderDate());
        reminderTime.setText(reminder.getReminderTime());
        reminderLocation.setText(reminder.getReminderLocation());
        reminderCheckBox.setChecked(reminder.getCheckedOff().equals(1));
        reminderAlertBox.setChecked(reminder.getReminderAlert().equals(1));

        //Reminder Priority
        String prioText="";
        for (int i=0;i<reminder.getReminderPriority();i++){
            prioText = prioText.concat("! ");
        }
        if (prioText.isEmpty()){
            reminderPriorty.setText("None");
        } else {
            reminderPriorty.setText(prioText);
        }

    }

    public void alertMeListener (View v) {
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {
            System.out.println("Check the reminder off");
            db.checkOffAlert(reminder.getReminderId(), 1);
        } else {
            System.out.println("Uncheck the reminder");
            db.checkOffAlert(reminder.getReminderId(), 0);
        }
    }

    public void checkboxListener (View v) {
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {
            System.out.println("Check the reminder off");
            db.checkOffReminder(reminder.getReminderId(), 1);
        } else {
            System.out.println("Uncheck the reminder");
            db.checkOffReminder(reminder.getReminderId(), 0);
        }
    }

    public void launchEditReminderActivity (View v) {
        Intent i = new Intent(this, EditReminderActivity.class);
        i.putExtra("ReminderId", reminder.getReminderId().toString());
        startActivity(i);
    }

    public void backButtonListener (View v) {
        finish();
    }

    @Override
    protected void onPause () {
        super.onPause();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateReminderInfo();
    }

}