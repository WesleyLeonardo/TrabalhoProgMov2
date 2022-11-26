package com.example.wesleyleonardodasilvatrabalho3.dao;

import android.view.Menu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wesleyleonardodasilvatrabalho3.entidades.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public void insert(User user);

    @Update
    public void update(User user);

    @Delete
    public void delete(User user);

    @Query("SELECT nome FROM User WHERE login = :login")
    public String getNome(String login);

    @Query("SELECT * FROM User WHERE login = :login AND senha = :senha")
    public User login(String login, String senha);

    @Query("DELETE FROM User WHERE login = :login")
    public void deleteByLogin(String login);

    @Query("SELECT login FROM User WHERE login = :login")
    public String getByLogin(String login);

    @Query("SELECT * FROM User")
    public List<User> getAll();

    @Query("SELECT * FROM User ORDER BY nome")
    public List<User> getOrganizado();
}
