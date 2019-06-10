package com.example.greekmythology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greekmythology.database.AppDatabase;
import com.example.greekmythology.model.GodItem;

import static com.example.greekmythology.EditActivity.ITEM_EDIT_NAME;
import static com.example.greekmythology.GodItemAdapter.ITEM_NAME;

public class DetailActivity extends AppCompatActivity {

    private AppDatabase db;
    TextView name, characteristics, description;
    GodItem item;
    String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get db
        db = AppDatabase.getInstance(this);

        //if intent is from edit set new name
        if (getIntent().getExtras().getString(ITEM_NAME) == null) {
            itemName = getIntent().getExtras().getString(ITEM_EDIT_NAME);
        } else {
             itemName = getIntent().getExtras().getString(ITEM_NAME);
        }

        item = db.godItemDao().findByName(itemName);

        setTitle(item.getName());

        name = findViewById(R.id.detail_god_name);
        characteristics = findViewById(R.id.detail_god_characteristics);
        description = findViewById(R.id.detail_god_description);

        name.setText(item.getName());
        characteristics.setText(item.getCharacteristics());
        description.setText(item.getDetailedDescription());

        Toast.makeText(this, "Received item: " + item.getName(), Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get current menu item id
        int id = item.getItemId();

        // if delete all is selected, delete all records and update adapter and recycleview
        if (id == R.id.action_edit) {
            String itemId = this.item.getName();
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(ITEM_NAME, itemId);
            this.startActivity(intent);
        }

        if (id == R.id.action_delete) {
            db.godItemDao().deleteGod(this.item);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
