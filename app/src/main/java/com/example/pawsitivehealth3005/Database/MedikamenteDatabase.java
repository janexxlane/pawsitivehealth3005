package com.example.pawsitivehealth3005.Database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pawsitivehealth3005.Datum_heute;

@Database(entities = {MedikamenteEntity.class},version=2)
public abstract class MedikamenteDatabase extends RoomDatabase{
    private static MedikamenteDatabase medikamenteDatabase;
    public abstract MedikamenteDAO medikamenteDAO();

    public static synchronized MedikamenteDatabase getInstance(Context context) {
        if (medikamenteDatabase == null) {
            medikamenteDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            MedikamenteDatabase.class, "medikamentedatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return medikamenteDatabase;
    }


}
