package com.example.pawsitivehealth3005.Database;

import androidx.room.Dao;
import androidx.room.Insert;


@Dao
public interface StammdatenDAO_Datumheute {

@Insert
void insertStammdaten(StammdatenEntity stammdaten);

}
