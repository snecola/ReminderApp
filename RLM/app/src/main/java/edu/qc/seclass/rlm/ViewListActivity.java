package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewListActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private String listName;
    private String query;
    private ArrayList<Reminder> reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        db = new DatabaseHelper(this);


        //Get Intent
        Intent i = getIntent();
        listName = i.getStringExtra("ListName");
        try {
            query = i.getStringExtra("query");
        } catch (Exception e){
            e.printStackTrace();
        }


        TextView heading = findViewById(R.id.listName);
        heading.setPadding(0, 0, 1, 0);
        heading.setText(listName);

        populateReminderList(listName);

        if (listName.equals("All") || listName.equals("Today") || listName.equals("Search")) {
            Button editButton = findViewById(R.id.editButton);
            editButton.setVisibility(View.GONE);
        }

    }

    private void populateReminderList (String listName) {
        //TODO: Call the query to get all reminders for List where ListName = listName
        LinearLayout listOfReminders = findViewById(R.id.listView);
        listOfReminders.removeAllViewsInLayout();
        if (listName.equals("All")) {
            reminderList = db.getAllDataReminder();
        } else if (listName.equals("Today")) {
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0); // same for minutes and seconds
            today.set(Calendar.MINUTE, 0); // same for minutes and seconds
            today.set(Calendar.SECOND, 0); // same for minutes and seconds
            System.out.println(today.toString());
            String todaysDate = (today.get(Calendar.MONTH)+1) + "/" + today.get(Calendar.DAY_OF_MONTH) + "/" + today.get(Calendar.YEAR);
            System.out.println(todaysDate);
            reminderList = db.getReminderByDate(todaysDate);
        } else if (listName.equals("Search")) {
            reminderList = db.getReminderBySearch(query);
        }
        else {
            reminderList = db.getReminderByListName(listName);
        }

        for (Reminder r : reminderList) {
            System.out.println(r.toString());
            Button b = new Button(ViewListActivity.this);
            String buttonText = r.getReminderName() + " " + r.getReminderTime() + " " + r.getReminderDate();
            b.setText(buttonText);
            if (r.getCheckedOff().equals(1)){
                b.setBackgroundColor(Color.rgb(90,238,90));
            } else {
                b.setBackgroundColor(Color.WHITE);
            }
            b.setTextColor(Color.BLACK);
            b.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            b.setId(r.getReminderId());
            b.setTag(r.getReminderId());
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchViewReminderActivity(v);
                }
            });
            listOfReminders.addView(b);
        }

    }

    private void addReminderToList (Reminder r) {

    }

    public void launchViewReminderActivity (View v) {
        Intent i = new Intent(this, ViewReminderActivity.class);
        String reminderId = v.getTag().toString();
        System.out.println(reminderId);
        i.putExtra("ReminderId", reminderId);
        startActivity(i);
    }

    public void launchNewReminderActivity (View v) {
        Intent i = new Intent(this, NewReminderActivity.class);
        i.putExtra("CurrentList", listName);
        startActivity(i);
    }

    public void backButtonListener (View v) {
        finish();
    }

    public void editButtonListener (View v) {
        Intent i = new Intent(this, EditListActivity.class);
        i.putExtra("listName", listName);
        startActivity(i);
    }

    public void uncheckAllRemindersInList (View v) {
        for (Reminder r : reminderList){
            db.checkOffReminder(r.getReminderId(), 0);
        }
        populateReminderList(listName);
    }

    @Override
    protected void onPause () {
        super.onPause();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateReminderList(listName);
    }
}