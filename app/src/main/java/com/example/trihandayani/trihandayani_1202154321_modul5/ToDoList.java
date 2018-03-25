package com.example.trihandayani.trihandayani_1202154321_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.LinkedList;

public class ToDoList extends AppCompatActivity {

    SharedPreferences sp;
    RecyclerView rv;
    RecyclerViewAdapter adapter;
    LinkedList<ToDoItem> list = new LinkedList<>();
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        sqLiteHelper = new SQLiteHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRecyclerView();

        sp = PreferenceManager.getDefaultSharedPreferences(this); //set or store last selected from list preference
        String color = sp.getString(getString(R.string.key_color), "#ff6d6d"); //#ff6d6d is the default color

        //rv = (RecyclerView) findViewById(R.id.recyclerViewMain); //background color of recycler view layout that need to be changed
        rv.setBackgroundColor(Color.parseColor(color)); //set the color to layout

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAdd = new Intent(getApplicationContext(), AddToDoList.class);
                startActivity(toAdd);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish(); //this activity will close when open the Setting Activity
            Intent setting = new Intent(getApplicationContext(), Settings.class);
            startActivity(setting);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setRecyclerView(){
        list = sqLiteHelper.selectAll();
        rv = (RecyclerView) findViewById(R.id.recyclerViewMain);
        adapter = new RecyclerViewAdapter(this, list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new SwipeHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Log.d("new name : ", data.getStringExtra("activity_name"));
            Log.d("new desc : ", data.getStringExtra("desc"));
            Log.d("new priority : ", data.getStringExtra("priority"));
            sqLiteHelper.saveData(new ToDoItem(data.getStringExtra("activity_name"),
                    data.getStringExtra("desc"), data.getStringExtra("priority")));
        }

        setRecyclerView();
        adapter.notifyDataSetChanged();
    }
}
