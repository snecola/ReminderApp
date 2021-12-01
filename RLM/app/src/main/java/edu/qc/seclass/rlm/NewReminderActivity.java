package edu.qc.seclass.rlm;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;



public class NewReminderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText reminderNameField;
    private EditText reminderDescField;
    private EditText dateTextField;
    private EditText timeTextField;
    private EditText repeatTextField;
    private EditText priorityTextField;
    private EditText locationTextField;

    private Integer listId;
    private String repeatType;
    private String pLevel;
    private String locationText;

    ImageButton createreminderClock;
    ImageButton createreminderCalender;
    ImageButton createreminderRepeat;
    ImageButton createreminderPriority;
    ImageButton createreminderLocation;



    Context context = this;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        reminderNameField = findViewById(R.id.newreminderNameInput);
        reminderDescField = findViewById(R.id.newreminderNotesInput);
        dateTextField = findViewById(R.id.createreminderCalenderetext);
        timeTextField = findViewById(R.id.createreminderClocktext);
        repeatTextField = findViewById(R.id.createreminderRepeattext);
        priorityTextField = findViewById(R.id.createreminderPrioritytext);
        locationTextField = findViewById(R.id.createreminderLocationtext);

        db = new DatabaseHelper(this);

        Intent i = getIntent();
        try {
            String currentList = i.getStringExtra("CurrentList");
        } catch (Exception e) {
            System.out.println("Null Current List");
        }

        ImageButton calendarButton = (ImageButton) findViewById(R.id.createreminderCalender);
        ImageButton clockButton = (ImageButton) findViewById(R.id.createreminderClock);
        ImageButton repeatButton = (ImageButton) findViewById(R.id.createreminderRepeat);
        ImageButton priorityButton = (ImageButton) findViewById(R.id.createreminderPriority);
        ImageButton locationButton = (ImageButton) findViewById(R.id.createreminderLocation);
        ImageButton selectListButton = (ImageButton) findViewById(R.id.selectListButton);


        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
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
        selectListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectList();
            }
        });

    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date =  (month+1) + "/" + dayOfMonth + "/" + year;
        dateTextField.setText(date);
    }
    Calendar calender = Calendar.getInstance();

        final int hour = calender.get(Calendar.HOUR_OF_DAY);
        final int minute = calender.get(Calendar.MINUTE);

    public void timepicker() {
            TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
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
            },hour, minute,android.text.format.DateFormat.is24HourFormat(context));
            timePickerDialog.show();
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

    public void selectList() {
        ArrayList<ReminderList> reminderLists;
        reminderLists = db.getAllDataList();
        String[] listNames = new String[reminderLists.size()];
        for (int i=0;i<reminderLists.size();i++) {
            listNames[i]= reminderLists.get(i).getListName();
        }

        EditText listTextInput = findViewById(R.id.newreminderListSelect);
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

    public void addReminder (View v) {
        //TODO: When add reminder UXUI is done, finish this part
        String nameText = reminderNameField.getText().toString();
        String descText = reminderDescField.getText().toString();
        String dateText = dateTextField.getText().toString();
        String timeText = timeTextField.getText().toString();
        Integer repeatText = Integer.parseInt(repeatTextField.getText().toString());
        Integer priorityNum = Integer.parseInt(priorityTextField.getText().toString());
        String locationText = locationTextField.getText().toString();

        db.insertReminderData(
                listId,
                nameText,
                locationText,
                descText,
                timeText,
                dateText,
                repeatText,
                priorityNum,
                0
        );
        finish();
    }

    public void repeatableListener (View v) {
        //TODO: Display a horizonal RadioList that M-
    }

    public void cancelAddReminder (View v) {
        finish();
    }





}
