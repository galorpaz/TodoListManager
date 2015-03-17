package com.example.gali.todolistmanager;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gali on 17-Mar-15.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);


        if ( position%2 != 0) {
            view.setBackgroundResource(android.R.color.holo_blue_dark);
        } else {
            view.setBackgroundResource(android.R.color.holo_red_light);
        }
        return view;
    }

}
