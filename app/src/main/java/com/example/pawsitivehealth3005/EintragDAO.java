package com.example.pawsitivehealth3005;

import androidx.room.Dao;
import androidx.room.Insert;



@Dao
public interface EintragDAO {

    @Insert
    void insertMedikament(com.example.pawsitivehealth3005.DailyEintrag medikament);
}

