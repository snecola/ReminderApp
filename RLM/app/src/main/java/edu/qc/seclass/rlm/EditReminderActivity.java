package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class EditReminderActivity extends AppCompatActivity {

    DatabaseHelper db;
    Reminder reminder;

    private EditText reminderNameField;
    private EditText reminderDescField;
    private EditText reminderListField;
    private EditText dateTextField;
    private EditText timeTextField;
    private EditText repeatTextField;
    private EditText priorityTextField;
    private EditText locationTextField;

    private Integer listId;
    private String repeatType;
    private String pLevel;
    private String locationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        Intent i = getIntent();
        String reminderId = i.getStringExtra("ReminderId");

        db = new DatabaseHelper(this);
        reminder = db.getReminderByID(Integer.parseInt(reminderId)).get(0);

        reminderNameField = findViewById(R.id.editreminderNameInput);
        reminderDescField = findViewById(R.id.editreminderNotesInput);
        reminderListField = findViewById(R.id.editreminderListSelect);
        dateTextField = findViewById(R.id.editreminderCalenderetext);
        timeTextField = findViewById(R.id.editreminderClocktext);
        repeatTextField = findViewById(R.id.editreminderRepeattext);
        priorityTextField = findViewById(R.id.editreminderPrioritytext);
        locationTextField = findViewById(R.id.editreminderLocationtext);
        listId = reminder.getListId();
        try {
            populateEditFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageButton calendarButton = (ImageButton) findViewById(R.id.createreminderCalender);
        ImageButton clockButton = (ImageButton) findViewById(R.id.createreminderClock);
        ImageButton repeatButton = (ImageButton) findViewById(R.id.createreminderRepeat);
        ImageButton priorityButton = (ImageButton) findViewById(R.id.createreminderPriority);
        ImageButton locationButton = (ImageButton) findViewById(R.id.createreminderLocation);
        ImageButton selectListButton = (ImageButton) findViewById(R.id.selectListButton);


        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepicker();
            }
        });
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatType();
            }
        });
        priorityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityset();
            }
        });
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationset();
            }
        });
    }

    public void updateReminder (View v) {
        db.updateReminderData(
                reminder.getReminderId(),
                listId,
                reminderNameField.getText().toString(),
                locationTextField.getText().toString(),
                reminderDescField.getText().toString(),
                timeTextField.getText().toString(),
                dateTextField.getText().toString(),
                Integer.parseInt(repeatTextField.getText().toString()),
                Integer.parseInt(priorityTextField.getText().toString()),
                reminder.getCheckedOff()
        );
        Intent i = new Intent(this, ViewReminderActivity.class);
        i.putExtra("ReminderId", reminder.getReminderId().toString());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void cancelEditReminder (View v) {
        finish();
    }

    private void populateEditFields () throws Exception {
        reminderNameField.setText(reminder.getReminderName());
        reminderDescField.setText(reminder.getReminderDescription());
        dateTextField.setText(reminder.getReminderDate());
        timeTextField.setText(reminder.getReminderTime());
        repeatTextField.setText(Integer.toString(reminder.getReminderAlert()));
        priorityTextField.setText(Integer.toString(reminder.getReminderPriority()));
        locationTextField.setText(reminder.getReminderLocation());
        ReminderList rl = db.getListByID(reminder.getListId());
        reminderListField.setText(rl.getListName());
    }

    public void deleteReminder (View v) {
        String listName = null;
        try {
            ReminderList rl = db.getListByID(reminder.getListId());
            listName = rl.getListName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.deleteReminderData(reminder.getReminderId());

        if (listName==null) {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else {
            Intent i = new Intent(this, ViewListActivity.class);
            i.putExtra("ListName", listName);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

    }

    public void selectList(View v) {
        ArrayList<ReminderList> reminderLists;
        reminderLists = db.getAllDataList();
        String[] listNames = new String[reminderLists.size()];
        for (int i=0;i<reminderLists.size();i++) {
            listNames[i]= reminderLists.get(i).getListName();
        }

        EditText listTextInput = findViewById(R.id.editreminderListSelect);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select List");
        builder.setItems(listNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listTextInput.setText(listNames[which]);
                listId = Integer.parseInt(reminderLists.get(which).getListId());
                System.out.println(listTextInput + " " + listId);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void priorityset() {
        final String[] p = new String[3];

        p[0] = "Low";
        p[1] = "Medium";
        p[2] = "High";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Priority Level");
        builder.setItems(p, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
                String priorityInt = Integer.toString(item+1);
                priorityTextField.setText(priorityInt);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void repeatType() {
        final String[] day = new String[7];

        day[0] = "Saturday";
        day[1] = "Sunday";
        day[2] = "Monday";
        day[3] = "Tuesday";
        day[4] = "Wednesday";
        day[5] = "Thursday";
        day[6] = "Friday";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(day, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {
//                    repeatType = day[item];
//                    repeatTextField.setText(repeatType);
//                    repeatTextField.setText("Repeats Every " + repeatType);
                repeatTextField.setText(Integer.toString(item));
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
    public void locationset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Location");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                locationTextField.setText(locationText);
                locationText = input.getText().toString();
                locationTextField.setText(locationText);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    Calendar calender = Calendar.getInstance();
    final int hour = calender.get(Calendar.HOUR_OF_DAY);
    final int minute = calender.get(Calendar.MINUTE);

    public void timepicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(minute < 10 )  timeTextField.setText(hourOfDay + ":0" + minute);
                else timeTextField.setText(hourOfDay + ":" + minute);
                String M = Integer.toString(minute);
                String H = Integer.toString(hourOfDay%12);
                String AP;
                if(minute < 10)  M = "0" + M;
                if(hourOfDay%12 == 0) H = "12";
                AP = hourOfDay/12 == 1 ? "PM" : "AM";
                timeTextField.setText(H + ":" + M + " "  + AP);
            }
        },hour, minute,android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }
}