package com.example.a200506.sampleroom.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class SampleDataBase extends RoomDatabase {
    private static SampleDataBase instance;

    public abstract UserDao userDao();

    public static void initInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context,
                    SampleDataBase.class,
                    "Sample.db")
                    .build();
        }
    }

    public static SampleDataBase getInstance() {
        return instance;
    }
}
