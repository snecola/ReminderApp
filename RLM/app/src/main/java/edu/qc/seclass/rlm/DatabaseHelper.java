package edu.qc.seclass.rlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "reminder.db";

    public static final String TABLE_REMINDER = "REMINDER_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LIST";
    public static final String COL_3 = "Reminder_Name";
    public static final String COL_4 = "Reminder_Location";
    public static final String COL_5 = "Reminder_Description";
    public static final String COL_6 = "Reminder_Time";
    public static final String COL_7 = "Reminder_Date";
    public static final String COL_8 = "Reminder_Alert";
    public static final String COL_9 = "Reminder_Priority";
    public static final String COL_10 = "Checked_Off";

    public static final String TABLE_LIST = "LIST_TABLE";
    public static final String listCOL_1 = "ID";
    public static final String listCOL_2 = "List_Name";
    public static final String listCOL_3 = "List_Color";
    public static final String listCOL_4 = "List_Priority";

    public static final String CREATE_REMINDER_TABLE =
            "CREATE TABLE \"REMINDER_TABLE\" (\n" +
                    "\t\"ID\"\tINTEGER UNIQUE,\n" +
                    "\t\"LIST\"\tINTEGER,\n" +
                    "\t\"Reminder_Name\"\tTEXT,\n" +
                    "\t\"Reminder_Location\"\tTEXT,\n" +
                    "\t\"Reminder_Description\"\tTEXT,\n" +
                    "\t\"Reminder_Time\"\tTEXT,\n" +
                    "\t\"Reminder_Date\"\tTEXT,\n" +
                    "\t\"Reminder_Alert\"\tINTEGER,\n" +
                    "\t\"Reminder_Priority\"\tINTEGER,\n" +
                    "\t\"Checked_Off\"\tINTEGER,\n" +
                    "\tPRIMARY KEY(\"ID\" AUTOINCREMENT)\n" +
                    ");";
    public static final String CREATE_LIST_TABLE =
            "CREATE TABLE \"LIST_TABLE\" (\n" +
                    "\"ID\" INTEGER, " +
                    "\"List_Name\" TEXT UNIQUE,\n" +
                    "\"List_Color\" TEXT,\n" +
                    "\"List_Priority\" INTEGER," +
                    "PRIMARY KEY(\"ID\" AUTOINCREMENT)\n" +
                    ");";

    /*
     * DatabaseHelper
     * @param context - The main activity the app is running
     * Creates the DatabaseHelper using the superclasses' constructor
     * **/
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /*
     * onCreate
     * @param db - an object to run SQL on
     * Creates the two tables in the DB
     * **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST_TABLE);
        db.execSQL(CREATE_REMINDER_TABLE);
    }

    /*
     * onUpgrade
     * @params db - an object to run SQL on, old Version - marks the old version, newVersion  - marks the old version
     * Updates the the database by dropping the tables and recreating the DB
     * **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

    /*
     * insertReminderData
     * @params All columns in the TABLE_REMINDER is a separate params
     * Inserts a reminder into the table TABLE_REMINDER and returns if it was successful
     * **/
    public boolean insertReminderData(Integer rem_List, String rem_Name, String rem_Loc, String rem_Desc,
                                      String rem_Time, String rem_Date, Integer rem_Alert, Integer rem_Prio, Integer rem_Check) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, rem_List);
        contentValues.put(COL_3, rem_Name);
        contentValues.put(COL_4, rem_Loc);
        contentValues.put(COL_5, rem_Desc);
        contentValues.put(COL_6, rem_Time);
        contentValues.put(COL_7, rem_Date);
        contentValues.put(COL_8, rem_Alert);
        contentValues.put(COL_9, rem_Prio);
        contentValues.put(COL_10, rem_Check);
        long result = db.insert(TABLE_REMINDER, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /*
     * insertListData
     * @params All columns in the TABLE_LIST is a separate params
     * Inserts a reminder into the table TABLE_LIST and returns if it was successful
     * **/
    public boolean insertListData(String list_Name, String list_Color, String list_Priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(listCOL_2, list_Name);
        contentValues.put(listCOL_3, list_Color);
        contentValues.put(listCOL_4, list_Priority);
        long result = db.insert(TABLE_LIST, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /*
     * updateReminderData
     * @params All columns in the TABLE_REMINDER is a separate params
     * Updates an entry DB's  TABLE_REMINDER table
     * **/
    public boolean updateReminderData(Integer rem_Id, Integer rem_List, String rem_Name, String rem_Loc, String rem_Desc,
                                      String rem_Time, String rem_Date, Integer rem_Alert, Integer rem_Prio, Integer rem_Check) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, rem_List);
        contentValues.put(COL_3, rem_Name);
        contentValues.put(COL_4, rem_Loc);
        contentValues.put(COL_5, rem_Desc);
        contentValues.put(COL_6, rem_Time);
        contentValues.put(COL_7, rem_Date);
        contentValues.put(COL_8, rem_Alert);
        contentValues.put(COL_9, rem_Prio);
        contentValues.put(COL_10, rem_Check);
        db.update(TABLE_REMINDER, contentValues, "ID = ?", new String[]{rem_Id.toString()});
        return true;
    }

    public void checkOffReminder(Integer reminderId, Integer checkedOff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_10, checkedOff);
        db.update(TABLE_REMINDER, contentValues, "ID = ?", new String[]{reminderId.toString()});
    }
    public void checkOffAlert(Integer reminderId, Integer alertMe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, alertMe);
        db.update(TABLE_REMINDER, contentValues, "ID = ?", new String[]{reminderId.toString()});
    }

    /*
     * updateListData
     * @params All columns in the TABLE_LIST is a separate params
     * Updates an entry DB's TABLE_LIST table
     * **/
    public boolean updateListData(String list_Name, String new_list_Name, String list_Color, String list_Priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(listCOL_2, new_list_Name);
        contentValues.put(listCOL_3, list_Color);
        contentValues.put(listCOL_4, list_Priority);
        db.update(TABLE_LIST, contentValues, listCOL_2 + " = ?", new String[]{list_Name});
        return true;
    }
    /*
     * deleteReminderData
     * @param id - the id of the Reminder entry in the TABLE_REMINDER table in the DB
     * Deletes a Reminder via id
     * **/
    public Integer deleteReminderData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_REMINDER, "ID = ?", new String[]{id.toString()});

    }
    /*
     * deleteReminderData
     * @param id - the id of the ReminderList entry in the TABLE_LIST table in the DB
     * Deletes a List via id
     * **/
    public Integer deleteListData(String listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_LIST, listCOL_2 + " = ?", new String[]{listName});

    }
    /*
     * getAllDataReminder
     * @param null
     * As the name implies it returns an ArrayList of Reminders with all the data in the table
     * TABLE_REMINDER
     * **/
    public ArrayList<Reminder> getAllDataReminder() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_REMINDER, null);

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.valueOf(res.getString(0)),
                        Integer.valueOf(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.valueOf(res.getString(7)),
                        Integer.valueOf(res.getString(8)),
                        Integer.valueOf(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;
    }/*
     * getAllDataList
     * @param null
     * As the name implies it returns a Cursor with all the data in the table TABLE_LIST
     * **/
//    public Cursor getAllDataList() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from " + TABLE_LIST, null);
//        return res;
//    }
    /*
    * getAllDataList
    * @param null
    * As the name implies it returns an ArrayList of ReminderLists with all the data
    * in the table TABLE_LIST
    * **/
    public ArrayList<ReminderList> getAllDataList () {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_LIST, null);

        ArrayList<ReminderList> reminderListArrayList = new ArrayList<>();

        if(res.moveToFirst()) {
            do {
                reminderListArrayList.add(new ReminderList(
                        res.getString(0),
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        getReminderByListID(Integer.parseInt(res.getString(0)))
                ));
            } while (res.moveToNext());
        }

        res.close();

        return reminderListArrayList;
    }

    /*
     * getReminderByID
     * @param id - the id of the desired Reminder in the DB as an int
     * Returns an ArrayList of Reminders with the given id
     * **/
    public ArrayList<Reminder> getReminderByID(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM REMINDER_TABLE WHERE ID = ?", new String[]{id.toString()});
//        Cursor res = db.query(TABLE_REMINDER, new String[]{COL_1,
//                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8}, COL_1 + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.valueOf(res.getString(0)),
                        Integer.valueOf(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.valueOf(res.getString(7)),
                        Integer.valueOf(res.getString(8)),
                        Integer.valueOf(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;
    }
    /*
     * getReminderByID
     * @param reminderName- A string that is similar to or equal to the name of the Reminder one
     * is searching for.
     * Returns an ArrayList of Reminders that start with or is equal to the reminderName.
     * **/
    public ArrayList<Reminder> getReminderBySearch(String reminderName) {
        SQLiteDatabase db = this.getReadableDatabase();

        reminderName = reminderName.concat("%");

        Cursor res = db.rawQuery("SELECT * FROM REMINDER_TABLE WHERE Reminder_Name LIKE ?",
                new  String[]{reminderName});
//
//        Cursor res = db.query(TABLE_REMINDER, new String[]{COL_1,
//                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8, COL_9, COL_10}, COL_3 + "LIKE ?",
//                new String[]{reminderName}, null, null, null, null);

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.valueOf(res.getString(0)),
                        Integer.valueOf(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.valueOf(res.getString(7)),
                        Integer.valueOf(res.getString(8)),
                        Integer.valueOf(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;

    }

    /*
     * getReminderByLISTID
     * @param id - the listid of the desired Reminder in the DB as an int
     * Returns an ArrayList of the Reminders with the given listid
     * **/
    public ArrayList<Reminder> getReminderByListID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.query(TABLE_REMINDER, new String[]{COL_1,
                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8, COL_9, COL_10}, COL_2 + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.valueOf(res.getString(0)),
                        Integer.valueOf(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.valueOf(res.getString(7)),
                        Integer.valueOf(res.getString(8)),
                        Integer.valueOf(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;
    }
    /*
     * getReminderByLISTID
     * @param listName - the name of the list of the desired Reminder
     * Returns an ArrayList of the Reminders that are in a ReminderList with the given listName.
     * **/
    public ArrayList<Reminder> getReminderByListName(String listName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor queryListName = db.query(TABLE_LIST, new String[]{listCOL_1}, listCOL_2 + "=?",
                new String[]{listName}, null, null, null, null);
        String listId="0";
        if (queryListName.moveToFirst()) {
            do {
                listId=queryListName.getString(0);
            } while(queryListName.moveToNext());
        }
        queryListName.close();

        Cursor res = db.query(TABLE_REMINDER, new String[]{COL_1,
                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8, COL_9, COL_10}, COL_2 + "=?",
                new String[]{listId}, null, null, null, null);

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.parseInt(res.getString(0)),
                        Integer.parseInt(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.parseInt(res.getString(7)),
                        Integer.parseInt(res.getString(8)),
                        Integer.parseInt(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;
    }
    /*
     * getReminderByDATE
     * @param date - The date that the Reminders we are looking for are set to. (note: might wanna change to a long)
     * Returns an ArrayList of the Reminders of the Reminders that set to that date
     * **/
    public ArrayList<Reminder> getReminderByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.query(TABLE_REMINDER, new String[]{COL_1,
                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8, COL_9, COL_10}, COL_7 + "=?",
                new String[]{date}, null, null, null, null);
        if (res != null)
            res.moveToFirst();

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.valueOf(res.getString(0)),
                        Integer.valueOf(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.valueOf(res.getString(7)),
                        Integer.valueOf(res.getString(8)),
                        Integer.valueOf(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;
    }
    /*
     * getReminderByPriority
     * @param priority - The priority that the ReminderLists we are looking for are set to.
     * Returns an ArrayList of the Reminders of the ReminderLists that have the given priority
     * **/
    public ArrayList<Reminder> getReminderByPriority(int priority) {
        SQLiteDatabase db = this.getReadableDatabase();

        //TODO: FIX THIS QUERY??
        Cursor res = db.query(TABLE_LIST, new String[]{COL_1,
                        COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8}, listCOL_4 + "=?",
                new String[]{String.valueOf(priority)}, null, null, listCOL_4, null);

        ArrayList<Reminder> reminderArrayList = new ArrayList<>();

        if (res.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                reminderArrayList.add(new Reminder(
                        Integer.valueOf(res.getString(0)),
                        Integer.valueOf(res.getString(1)),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        Integer.valueOf(res.getString(7)),
                        Integer.valueOf(res.getString(8)),
                        Integer.valueOf(res.getString(9))
                ));
            } while (res.moveToNext());
            // moving our cursor to next.
        }

        res.close();

        return reminderArrayList;
    }
    /*
     * getLISTbyID
     * @param id - the id of the desired ReminderList in the DB as an int
     * Returns an ArrayList of the Reminders of the ReminderList with the given id
     * **/
    public ReminderList getListByID(int id) throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.query(TABLE_LIST, new String[]{listCOL_1,
                        listCOL_2, listCOL_3, listCOL_4}, listCOL_1 + "=?",
                new String[]{String.valueOf(id)}, COL_1, null, null, null);

        ReminderList reminderList;

        if(res.moveToFirst()) {
            reminderList = new ReminderList(
                    res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    getReminderByListID(Integer.parseInt(res.getString(0)))
            );
        } else {
            throw new Exception("Reminder list with id "+ id + " does not exist");
        }

        res.close();

        return reminderList;
    }
}