package com.example.wesleyleonardodasilvatrabalho3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wesleyleonardodasilvatrabalho3.dao.UserDAO;
import com.example.wesleyleonardodasilvatrabalho3.entidades.User;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "Trabalho3").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return INSTANCE;
    }



    public abstract UserDAO userDAO();
}
