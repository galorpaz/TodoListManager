package com.example.gali.todolistmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Todo_item> list = new ArrayList<>();
    private CustomAdapter adapter;
    static DateFormat format = new SimpleDateFormat("d-MM-yyyy");
   final Context context = this;

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
                TextView item_text = (TextView) view.findViewById(R.id.txtTodoTitle);
                String title = item_text.getText().toString();

                final Dialog itemDialog = new Dialog(context);
                itemDialog.setContentView(R.layout.item_alert_dialog);
                itemDialog.setTitle(title);
                if (title.toLowerCase().contains("call")) {
                    Pattern pattern = Pattern.compile("\\d");
                    Matcher matcher = pattern.matcher(title);
                    String number = "";
                    while (matcher.find()) {
                        number += title.substring(matcher.start(), matcher.end());
                    }
                    final String finalNumber = number;
                    Button callBtn = (Button) itemDialog.findViewById(R.id.menuItemCall);
                    callBtn.setVisibility(View.VISIBLE);
                    callBtn.setText(title);
                    callBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + finalNumber));
                            startActivity(callIntent);//
                        }
                    });
                }

                Button deleteBtn = (Button) itemDialog.findViewById(R.id.menuItemDelete);
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                        itemDialog.dismiss();
                    }
                });
                itemDialog.show();
                return true;
            }
//
        }); //end of setOnItemLongClickListener

        //
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Date date = (Date) bundle.get("dueDate");
            String dateS;
            if (date != null) {
                dateS = MainActivity.format.format(date);
            } else {
                dateS = "No due date";
            }
            String title = (String) bundle.get("title");
            if (!title.isEmpty()) {
                Todo_item todo_item = new Todo_item(title, dateS);
                list.add(todo_item);
                adapter.notifyDataSetChanged();
            }

        }
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
            Intent add_new_activity_intent = new Intent(this, AddNewTodoItemActivity.class);
            startActivity(add_new_activity_intent);

        }

        return super.onOptionsItemSelected(item);
    }
}

