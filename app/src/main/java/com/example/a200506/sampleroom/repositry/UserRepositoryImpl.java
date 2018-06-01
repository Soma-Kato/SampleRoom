package com.example.a200506.sampleroom.repositry;

import com.example.a200506.sampleroom.room.SampleDataBase;
import com.example.a200506.sampleroom.room.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Completable insert(final User user) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                SampleDataBase.getInstance().userDao().insert(user);
            }
        });
    }

    @Override
    public Completable insert(List<User> list) {
        return Observable.fromIterable(list)
                .flatMapCompletable(new Function<User, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final User user) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                SampleDataBase.getInstance().userDao().insert(user);
                            }
                        });
                    }
                });
    }

    @Override
    public Single<List<User>> findAll() {
        return SampleDataBase.getInstance().userDao().findAll();
    }

    @Override
    public Single<User> getByUserId(long userId) {
        return SampleDataBase.getInstance().userDao().getById(userId);
    }

    @Override
    public Completable deleteAll() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                SampleDataBase.getInstance().userDao().deleteAll();
            }
        });
    }
}
