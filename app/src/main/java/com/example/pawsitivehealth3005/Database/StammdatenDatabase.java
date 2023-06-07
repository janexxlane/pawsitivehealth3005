package com.example.pawsitivehealth3005.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pawsitivehealth3005.Datum_heute;


@Database(entities = {StammdatenEntity.class}, version = 1)
public abstract class StammdatenDatabase extends RoomDatabase {
    private static StammdatenDatabase stammdatenDatabase;


    public abstract StammdatenDAO_Datumheute stammdatenDAO_datumheute();

    public static synchronized StammdatenDatabase getInstance(Context context) {
        if (stammdatenDatabase == null) {
            stammdatenDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            StammdatenDatabase.class, "stammdatendatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return stammdatenDatabase;
    }
}