package com.example.greekmythology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greekmythology.database.AppDatabase;
import com.example.greekmythology.model.GodItem;

import static com.example.greekmythology.GodItemAdapter.ITEM_NAME;

public class EditActivity extends AppCompatActivity {

    public static final String ITEM_EDIT_NAME = "edit_item_name";
    Button saveButton;
    EditText editName, editCharacteristics, editDescription;
    private AppDatabase db;
    GodItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //get db
        db = AppDatabase.getInstance(this);

        String itemName = getIntent().getExtras().getString(ITEM_NAME);
        item = db.godItemDao().findByName(itemName);

        setTitle("Edit " + item.getName());

        saveButton = findViewById(R.id.saveGodButton);
        editName = findViewById(R.id.editGodName);
        editCharacteristics = findViewById(R.id.editGodCharacteristics);
        editDescription = findViewById(R.id.editGodDescription);

        // set text iof input fields
        editName.setText(item.getName());
        editCharacteristics.setText(item.getCharacteristics());
        editDescription.setText(item.getDetailedDescription());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);

                String updateName = editName.getText().toString();
                String updateCharacteristics = editCharacteristics.getText().toString();
                String updateDescription = editDescription.getText().toString();

                db.godItemDao().updateGod(new GodItem(updateName, updateCharacteristics, updateDescription));

                Toast.makeText(EditActivity.this, "You have updated: " + updateName, Toast.LENGTH_SHORT).show();

                intent.putExtra(ITEM_EDIT_NAME , updateName);

                startActivity(intent);
//                finish();
            }
        });
    }
}
