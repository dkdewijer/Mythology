package com.example.greekmythology.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gods")
public class GodItem  {
    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo
    private String characteristics;
    @ColumnInfo(name = "detailed_description")
    private String detailedDescription;


    public GodItem(String name, String characteristics, String detailedDescription) {
        this.name = name;
        this.characteristics = characteristics;
        this.detailedDescription = detailedDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    @Override
    public String toString() {
        return "GodItem{" +
                "name='" + name + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                '}';
    }
}
