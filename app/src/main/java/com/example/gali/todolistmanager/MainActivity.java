package com.example.gali.todolistmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    static ArrayList<Todo_item> list = new ArrayList<>();
    static CustomAdapter adapter;
    static DateFormat format = new SimpleDateFormat("d-MM-yyyy");
    public static Activity mApp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mApp ==  null){ mApp=this;}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listview = (ListView) findViewById(R.id.list_view);
        adapter = new CustomAdapter(this, R.layout.itemlistrow, list);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView item_text = (TextView) view.findViewById(R.id.item_text);
                String title = item_text.getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(title);
                alert.setCancelable(true);
                if (title.toLowerCase().contains("call")) {
                    Pattern pattern = Pattern.compile("\\d");
                    Matcher matcher = pattern.matcher(title);
                    String number = "";
                    while (matcher.find()){
                        number += title.substring(matcher.start(), matcher.end());
                    }
                    final String finalNumber = number;
                    alert.setPositiveButton(title, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:"+finalNumber));
                            startActivity(callIntent);//
                        }
                    });
                }
                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(adapter.getItem(position));
                                adapter.notifyDataSetChanged();
                            }
                        });
                alert.show();

                return true;
            }
//
        });
//        Spinner spinner = (Spinner)findViewById(R.id.spinner);
//        adapter.setDropDownViewResource(android.R.layout.activity_list_item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            //start the activity
            Intent add_new_activity_intent = new Intent(this, AddNewItemActivity.class);
            startActivity(add_new_activity_intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

