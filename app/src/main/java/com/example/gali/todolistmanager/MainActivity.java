package com.example.gali.todolistmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    ArrayList<String> list = new ArrayList<String>();
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listview = (ListView) findViewById(R.id.list_view);
        adapter = new CustomAdapter(this, R.layout.itemlistrow, list);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                TextView clickedView = (TextView) view;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(clickedView.getText())
                        .setCancelable(true)
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(adapter.getItem(position));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();

                return true;
            }
//
        });
//        Spinner spinner = (Spinner)findViewById(R.id.spinner);
//        adapter.setDropDownViewResource(android.R.layout.activity_list_item);
    }




        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.add) {
                EditText todoEditText = (EditText) findViewById(R.id.new_todo);
                String todoString = todoEditText.getText().toString();
                if (! todoString.isEmpty()){
                    list.add(todoString);
                    adapter.notifyDataSetChanged();
                    todoEditText.setText("");
                }
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
