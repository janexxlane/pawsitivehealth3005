package com.example.pawsitivehealth3005.Database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={SpaziergängeEntity.class},version=2)
public abstract class SpaziergängeDatabase extends RoomDatabase{

    private static SpaziergängeDatabase spaziergängeDatabase;
    public abstract SpaziergängeDAO spaziergängeDAO();

    public static synchronized SpaziergängeDatabase getInstance(Context context) {
        if (spaziergängeDatabase == null) {
            spaziergängeDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            SpaziergängeDatabase.class, "spaziergängedatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return spaziergängeDatabase;
    }
}
