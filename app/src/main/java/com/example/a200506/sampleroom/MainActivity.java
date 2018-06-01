package com.example.a200506.sampleroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.a200506.sampleroom.repositry.UserRepository;
import com.example.a200506.sampleroom.repositry.UserRepositoryImpl;
import com.example.a200506.sampleroom.room.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable disposable = new CompositeDisposable();
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRoom();
            }
        });

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAll();
            }
        });

        findViewById(R.id.getId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getByUserId();
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });
    }

    private void deleteAll() {
        DisposableCompletableObserver completableObserver = userRepository.deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        disposable.add(completableObserver);
    }

    private void setupRoom() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User(i, "test" + String.valueOf(i), i, "hage");
            list.add(user);
        }

        DisposableCompletableObserver completableObserver = userRepository.insert(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        disposable.add(completableObserver);
    }

    private void getAll() {
        DisposableSingleObserver singleObserver = userRepository.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        disposable.add(singleObserver);
    }

    private void getByUserId() {
        DisposableSingleObserver singleObserver = userRepository.getByUserId(70)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        disposable.add(singleObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        disposable = null;
    }
}
