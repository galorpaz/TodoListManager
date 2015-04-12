package com.example.gali.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by gali on 17-Mar-15.
 */
public class CustomAdapter extends ArrayAdapter<Todo_item> {

    public CustomAdapter(Context context, int resource, List<Todo_item> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.itemlistrow, null);
        TextView todo_task = (TextView) view.findViewById(R.id.item_text);
        TextView todo_date = (TextView) view.findViewById(R.id.item_date);
        Todo_item todo_item = MainActivity.list.get(position);
        //set the values
        todo_task.setText(todo_item.task);
        todo_date.setText(todo_item.date);
        if (!todo_item.date.equalsIgnoreCase("no due to date")) {
            Date date = null;
            try {
                date = MainActivity.format.parse(todo_date.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date now = new Date();
            if (date.before(now)) {//due to date has passed
                todo_date.setTextColor(Color.RED);
                todo_task.setTextColor(Color.RED);
            }
        }
        return view;
    }

}
