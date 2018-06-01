package com.example.a200506.sampleroom.repositry;

import com.example.a200506.sampleroom.room.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface UserRepository {
    Completable insert(User user);

    Completable insert(List<User> list);

    Single<List<User>> findAll();

    Single<User> getByUserId(long userId);

    Completable deleteAll();
}
