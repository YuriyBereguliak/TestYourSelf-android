package ua.edu.nulp.testyourself.data.datasource;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface UserDataSource {

    LiveData<List<User>> getAllUsers();

    LiveData<Boolean> createUser(User user);

    void deleteUser(User user);
}
