package com.example.pawsitivehealth3005.Database;


import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Dao
public interface SpaziergängeDAO {


    @Insert
    void insertSpaziergänge(SpaziergängeEntity spaziergängeEntity);

    }

