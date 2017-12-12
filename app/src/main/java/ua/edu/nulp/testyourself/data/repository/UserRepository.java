package ua.edu.nulp.testyourself.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;
import ua.edu.nulp.testyourself.data.datasource.UserDataSource;
import ua.edu.nulp.testyourself.data.db.user.UserDao;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class UserRepository implements UserDataSource {

    private ThreadExecutor mThreadExecutor;
    private UserDao mUserDao;

    public UserRepository(ThreadExecutor threadExecutor, UserDao userDao) {
        mThreadExecutor = threadExecutor;
        mUserDao = userDao;
    }

    //region UserDataSource
    @Override
    public LiveData<List<User>> getAllUsers() {
        final MutableLiveData<List<User>> data = new MutableLiveData<>();
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                data.postValue(mUserDao.getAllUsers());
            }
        });
        return data;
    }

    @Override
    public void createUser(final User user) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.createUser(user);
            }
        });
    }

    @Override
    public void deleteUser(final User user) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mUserDao.deleteUser(user);
            }
        });
    }
    //endregion
}

