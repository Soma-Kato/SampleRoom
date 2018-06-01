package com.example.a200506.sampleroom.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    Single<List<User>> findAll();

    @Query("SELECT * FROM User WHERE User.id = :userId LIMIT 1")
    Single<User> getById(long userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void updata(User user);

    @Query("DELETE FROM User")
    void deleteAll();
}
