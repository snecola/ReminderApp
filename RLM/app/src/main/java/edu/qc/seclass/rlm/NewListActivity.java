package edu.qc.seclass.rlm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NewListActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_newlist);
        db = new DatabaseHelper(this);
        userInput = findViewById(R.id.newlistName);

    }



    //User creates their list
    public void addList (View v) {

        ArrayList<ReminderList> reminderLists = db.getAllDataList();
        CharSequence suc = "Added list " + userInput.getText().toString() + "!";
        CharSequence err = "List already exists!";
        CharSequence no = "Please enter a list name!";
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        Toast success = Toast.makeText(context, suc, duration);
        Toast error = Toast.makeText(context, err, duration);
        Toast nothing = Toast.makeText(context, no, duration);
        System.out.println("I'm here with");
        if(userInput.length() == 0) {
            nothing.show();
        }
        if (userInput.getText().toString().equals("All") || userInput.getText().toString().equals("Today")) {
            error.show();
            finish();
            return;
        }
        db.insertListData(userInput.getText().toString(), "#000000", "1" );
        success.show();
        finish();
    }

    //User cancels their action
    public void cancelAddList (View v) {
        finish();
    }

}