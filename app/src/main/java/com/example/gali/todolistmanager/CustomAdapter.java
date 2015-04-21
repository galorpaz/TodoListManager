package com.example.gali.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Todo_item> {
    private List<Todo_item> list;
    public CustomAdapter(Context context, int resource, List<Todo_item> objects) {
        super(context, resource, objects);
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.itemlistrow, null);
        TextView todo_task = (TextView) view.findViewById(R.id.txtTodoTitle);
        TextView todo_date = (TextView) view.findViewById(R.id.txtTodoDueDate);
        Todo_item todo_item = this.list.get(position);
        //set the values
        todo_task.setText(todo_item.get_task());
        todo_date.setText(todo_item.get_date());
        if (!todo_item.get_date().equalsIgnoreCase("no due to date")) {
            Date date = null;
            try {
                date = MainActivity.format.parse(todo_date.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            if (sdf.format(now).compareTo(sdf.format(date))>0 ) {//due to date has passed
                todo_date.setTextColor(Color.RED);
                todo_task.setTextColor(Color.RED);
            }
        }
        return view;
    }

}
