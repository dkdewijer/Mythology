package com.example.greekmythology;

import android.os.Bundle;

import com.example.greekmythology.database.AppDatabase;
import com.example.greekmythology.model.GodItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    List<GodItem> gods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = AppDatabase.getInstance(this);
        gods = new ArrayList<>();

        GodItem god1 = new GodItem("Zeus", "Father of all gods", "ME LIKEEE");
        GodItem god2 = new GodItem("Aphrodite", "Goddess of love", "ME LIKEEE");
        GodItem god3 = new GodItem("Hades", "God of death", "ME LIKEEE");
        GodItem god4 = new GodItem("Poseidon", "God of sea", "ME LIKEEE");

        System.out.println(god1.toString());
        System.out.println(god2.toString());
        System.out.println(god3.toString());
        System.out.println(god4.toString());

        gods.add(god1);
        gods.add(god2);
        gods.add(god3);
        gods.add(god4);


        int items = db.godItemDao().countItems();

        if (items == 0){
           db.godItemDao().insertAll(gods);
            Log.i("DATA CREATION: ", "onCreate: DATA INSERTED");
        }   else {
            Log.e("DATA CREATION ", "DATA ALREADYEXISTS");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
