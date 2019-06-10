package com.example.greekmythology.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.greekmythology.model.GodItem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GodItemDao {

    @Insert
    void insertAll(List<GodItem> items);

    @Insert
    void insertGod (GodItem god);

    @Query("SELECT COUNT(*) FROM  gods")
    int countItems();

    @Query("SELECT * FROM gods ORDER BY name")
    List<GodItem> getAllGods();

    @Query("SELECT * FROM gods WHERE name = :name")
    GodItem findByName(String name);

    @Delete
    void deleteAllGods(List<GodItem> items);

    @Delete
    void deleteGod(GodItem god);

    @Update
    void updateGod(GodItem god);
}
