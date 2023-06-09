package com.example.pawsitivehealth3005.Database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={ArztpraxisEntity.class}, version=2)
public abstract class ArztpraxisDatabase extends RoomDatabase {

    private static ArztpraxisDatabase arztpraxisDatabase;
    public abstract ArztpraxisDAO arztpraxisDAO();

    public static synchronized ArztpraxisDatabase getInstance(Context context) {

            if (arztpraxisDatabase == null) {
                arztpraxisDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                ArztpraxisDatabase.class, "arztpraxisdatabase")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return arztpraxisDatabase;
        }

}

