package com.example.greekmythology.database;

import android.content.Context;

import com.example.greekmythology.model.GodItem;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GodItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract GodItemDao godItemDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app-database").allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

}
