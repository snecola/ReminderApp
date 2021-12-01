package edu.qc.seclass.rlm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button btnAddData;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(MainActivity.this);

        SearchView searchBar = findViewById(R.id.reminderSearch);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchReminders(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        populateMyLists();
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    // Launch the New List activity

    public void launchNewListActivity(View v) {
        Intent i = new Intent(this, NewListActivity.class);
        startActivity(i);
    }

    public void launchNewReminderActivity (View v) {
        Intent i = new Intent(this, NewReminderActivity.class);
        startActivity(i);
    }

    public void launchViewListActivity (View v) {
        Intent i = new Intent(this, ViewListActivity.class);
        //Pass in the List's information so the constructor in the ViewListActivity.java file can put together the list based on queries.
        String listName = ((Button)v).getText().toString();
        i.putExtra("ListName",listName);
        startActivity(i);
    }

    private void populateMyLists () {
        // TODO: Get a list of ReminderList(s) from DB
        ArrayList<ReminderList> reminderLists = db.getAllDataList();
        LinearLayout listOfLists = findViewById(R.id.listView);
        listOfLists.removeAllViewsInLayout();
        // TODO: Create a new view for each ReminderList in the ScrollableListView
        for (ReminderList r : reminderLists) {
            Button b = new Button(MainActivity.this);
            b.setText(r.getListName());
            b.setBackgroundColor(Color.WHITE);
            b.setTextColor(Color.BLACK);
            b.setId(Integer.parseInt(r.getListId()));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchViewListActivity(v);
                }
            });
            listOfLists.addView(b);
        }

    }

//    public void searchButtonListener (View v) {
//        SearchView searchBar = findViewById(R.id.reminderSearch);
//        String query = searchBar.getQuery().toString();
//        searchReminders(query);
//    }
    public void searchReminders (String query) {
        System.out.println(query);
        Intent i = new Intent(this, ViewListActivity.class);
        i.putExtra("ListName", "Search");
        i.putExtra("query", query);
        startActivity(i);
    }

    @Override
    protected void onPause () {
        super.onPause();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateMyLists();
    }

}

