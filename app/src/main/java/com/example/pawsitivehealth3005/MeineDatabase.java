package com.example.pawsitivehealth3005;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {com.example.pawsitivehealth3005.DailyEintrag.class}, version = 1)
public abstract class MeineDatabase extends RoomDatabase {
    private static MeineDatabase instance;

    public abstract com.example.pawsitivehealth3005.EintragDAO medikamentDao();

    public static synchronized MeineDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MeineDatabase.class, "meine_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}