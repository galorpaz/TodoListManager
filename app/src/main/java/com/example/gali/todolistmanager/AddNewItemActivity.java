package com.example.gali.todolistmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Date;

/**
 * Created by gali on 18-Mar-15.
 */
public class AddNewItemActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        setTitle("Add New Item");
        Button okBtn = (Button) findViewById(R.id.btnOk);
        okBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                add_new_todo();
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent main_activity = new Intent(v.getContext(), MainActivity.class);
                startActivity(main_activity);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void add_new_todo() {
        //extract the data
        String dateS;
        EditText todo_task = (EditText) findViewById(R.id.new_todo);
        DatePicker todo_date = (DatePicker) findViewById(R.id.todo_date);
        CheckBox no_date = (CheckBox) findViewById(R.id.NoDate);
        if (no_date.isChecked()) {
            //no due to date
            dateS = "no due to date";
        } else {
            Date date = new Date(todo_date.getCalendarView().getDate());
            dateS = MainActivity.format.format(date);
        }
        // create new todo_item object and add it to the list
        if (!todo_task.getText().toString().isEmpty()) {
            Todo_item todo_item = new Todo_item(todo_task.getText().toString(), dateS);
            MainActivity.list.add(todo_item);
            MainActivity.adapter.notifyDataSetChanged();
        }
        //return to main activity
        Intent main_activity = new Intent(this, MainActivity.class);
        startActivity(main_activity);
    }

}

