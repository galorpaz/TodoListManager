package com.example.gali.todolistmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todo_db";

    // Todotable name
    private static final String TABLE_TODO = "todo";

    // TodoTable Columns names
    private static final String KEY_TITLE = "title";
    private static final String KEY_DUE = "due";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     *  These is where we need to write create table statements.
     *  This is called when database is created.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_TITLE + " TEXT PRIMARY KEY," + KEY_DUE + " TEXT " + ")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    /*
     * This method is called when database is upgraded like modifying the table
     * structure, adding constraints to database etc
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);

        // Create tables again
        onCreate(db);
    }

    // Adding new todo_item
    public void addTodoItem(Todo_item todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.get_task()); // todo_item title
        values.put(KEY_DUE, todo.get_date()); // todo_item due date

        // Inserting Row
        db.insert(TABLE_TODO, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Contacts
    public ArrayList<Todo_item> getAllTodos() {
        ArrayList<Todo_item> todoList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Todo_item contact = new Todo_item(cursor.getString(0), cursor.getString(1));
                // Adding contact to list
                todoList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return todoList;
    }

    // Deleting single todo_item
    public void deleteTodo_item(Todo_item todo_item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_TITLE + " = ?",
                new String[]{String.valueOf(todo_item.get_task())});
        db.close();
    }

}
