package edu.qc.seclass.rlm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditListActivity extends AppCompatActivity {

    private DatabaseHelper db;
    String oldListName;
    EditText listNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        db = new DatabaseHelper(this);

        Intent i = getIntent();
        oldListName = i.getStringExtra("listName");

        listNameText = findViewById(R.id.editlistName);
        listNameText.setText(oldListName);


    }

    public void cancelEditList(View v) {
        finish();
    }

    public void editList (View v) {
        String newListName = listNameText.getText().toString();
        db.updateListData(oldListName, newListName, "#000000", "1");
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void deleteList (View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Removing this list will also delete all reminders in this list. Continue?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        deleteListHelper();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void deleteListHelper () {
        System.out.println("Delete");
        ArrayList<Reminder> reminderArrayList = db.getReminderByListName(oldListName);
        for (Reminder r : reminderArrayList) {
            db.deleteReminderData(r.getReminderId());
        }
        db.deleteListData(oldListName);

        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}
