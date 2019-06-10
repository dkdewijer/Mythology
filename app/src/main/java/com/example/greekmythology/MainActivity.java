package com.example.greekmythology;

import android.content.Intent;
import android.os.Bundle;

import com.example.greekmythology.database.AppDatabase;
import com.example.greekmythology.model.GodItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    List<GodItem> dummyGods;
    List<GodItem> godsList;
    RecyclerView recyclerView;
    GodItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get db
        db = AppDatabase.getInstance(this);

        fillWithDummyData();

        //get all gods in db
        godsList = db.godItemDao().getAllGods();
        adapter = new GodItemAdapter(this, godsList);

        recyclerView = findViewById(R.id.gods_view_list);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                startActivity(intent);
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
        // get current menu item id
        int id = item.getItemId();

        // if delete all is selected, delete all records and update adapter and recycleview
        if (id == R.id.action_delete_all) {
            //delete all records
            this.db.godItemDao().deleteAllGods(this.godsList);

            //get empty item list and set new adapter
            GodItemAdapter emptyAdapter = new GodItemAdapter(this, this.db.godItemDao().getAllGods());
            recyclerView.setAdapter(emptyAdapter);

            Toast.makeText(this, "All the records have been deleted", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillWithDummyData() {
        dummyGods = new ArrayList<>();
        dummyGods.add(new GodItem("Zeus", "Father of all gods", "Zeus is the sky and thunder god in ancient Greek religion, who rules as king of the gods of Mount Olympus."));
        dummyGods.add(new GodItem("Aphrodite", "Goddess of love", "Aphrodite is an ancient Greek goddess associated with love, beauty, pleasure, passion and procreation."));
        dummyGods.add(new GodItem("Hades", "God of death", "Hades in the ancient Greek religion and myth, is the god of the dead and the king of the underworld, with which his name became synonymous"));
        dummyGods.add(new GodItem("Poseidon", "God of sea", "Poseidon was one of the Twelve Olympians in ancient Greek religion and myth. He was god of the Sea and other waters; of earthquakes; and of horses."));

        int items = db.godItemDao().countItems();

        // use to update sample data
//        db.godItemDao().deleteAllGods(this.godsList);

        if (items == 0) {
            db.godItemDao().insertAll(dummyGods);
            Log.i("DATA CREATION: ", "onCreate: DATA INSERTED");
        } else {
            Log.e("DATA CREATION: ", "DATA ALREADY EXISTS");
        }
    }
}
