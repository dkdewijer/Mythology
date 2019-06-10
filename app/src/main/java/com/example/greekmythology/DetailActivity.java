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

import static com.example.greekmythology.EditActivity.CURRENT_ITEM_NAME;
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

        //check which item to get from the db
        if (getIntent().getExtras().getString(ITEM_NAME) != null) {
            itemName = getIntent().getExtras().getString(ITEM_NAME);
        } else if (getIntent().getExtras().getString(ITEM_EDIT_NAME) != null) {
            itemName = getIntent().getExtras().getString(ITEM_EDIT_NAME);
        } else {
            itemName = getIntent().getExtras().getString(CURRENT_ITEM_NAME);
        }
        item = db.godItemDao().findByName(itemName);
        setTitle(item.getName());

        //get text view
        name = findViewById(R.id.detail_god_name);
        characteristics = findViewById(R.id.detail_god_characteristics);
        description = findViewById(R.id.detail_god_description);

        //set textview
        name.setText(item.getName());
        characteristics.setText(item.getCharacteristics());
        description.setText(item.getDetailedDescription());
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

        if (id == R.id.action_edit) {
            String itemId = this.item.getName();
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(ITEM_NAME, itemId);
            startActivity(intent);
        }

        if (id == R.id.action_delete) {
            Intent intent = new Intent(this, MainActivity.class);
            db.godItemDao().deleteGod(this.item);
            onResume();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
