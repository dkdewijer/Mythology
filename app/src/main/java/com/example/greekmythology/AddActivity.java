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

public class AddActivity extends AppCompatActivity {
    Button saveButton;
    EditText addName, addCharacteristics, addDescription;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //get db
        db = AppDatabase.getInstance(this);

        setTitle("Add your God");

        saveButton = findViewById(R.id.saveAddGodButton);
        addName = findViewById(R.id.addGodName);
        addCharacteristics = findViewById(R.id.addGodCharacteristics);
        addDescription = findViewById(R.id.addGodDescription);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);

                String addNewName = addName.getText().toString();
                String addNewCharacteristics = addCharacteristics.getText().toString();
                String addNewDescription = addDescription.getText().toString();

                // add god to db
                db.godItemDao().insertGod(new GodItem(addNewName, addNewCharacteristics, addNewDescription));

                Toast.makeText(AddActivity.this, "Your god has been added: " + addNewName, Toast.LENGTH_SHORT).show();

                //go back to main activity
                onResume();
                startActivity(intent);
            }
        });

    }
}
