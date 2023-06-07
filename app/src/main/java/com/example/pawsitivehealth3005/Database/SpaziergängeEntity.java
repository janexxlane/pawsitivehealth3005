package com.example.pawsitivehealth3005.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "spaziergängetabelle")
public class SpaziergängeEntity {



    @PrimaryKey
    private int id;
    private String spaziergangName;
    private String stepCount;

    public void setSpaziergangName(String spaziergangName) {
        this.spaziergangName = spaziergangName;
    }
    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getSpaziergangName() {
        return spaziergangName;
    }

    public String getStepCount() {
        return stepCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }}