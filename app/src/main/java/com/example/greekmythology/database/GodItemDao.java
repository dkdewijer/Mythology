package com.example.greekmythology.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.greekmythology.model.GodItem;

import java.util.List;

@Dao
public interface GodItemDao {

    @Insert
    void insertAll(List<GodItem> items);

    @Insert
    void insertAll(GodItem... items);

    @Query("SELECT COUNT(*) FROM  gods")
    int countItems();
}
