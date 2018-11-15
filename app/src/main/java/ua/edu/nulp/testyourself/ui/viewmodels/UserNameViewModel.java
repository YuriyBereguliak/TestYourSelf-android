package ua.edu.nulp.testyourself.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import ua.edu.nulp.testyourself.data.datasource.UserDataSource;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class UserNameViewModel extends AndroidViewModel {

    private LifecycleOwner mLifecycleOwner;

    public UserNameViewModel(Application application) {
        super(application);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    //region UserNameViewModel
    public MutableLiveData<Boolean> saveUser(UserDataSource userDataSource, User user) {
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        userDataSource.createUser(user).observe(mLifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                mutableLiveData.postValue(aBoolean);
            }
        });
        return mutableLiveData;
    }

    public LiveData<List<User>> getAllUsers(UserDataSource userDataSource) {
        final MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
        userDataSource.getAllUsers().observe(mLifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                mutableLiveData.postValue(users);
            }
        });
        return mutableLiveData;
    }
    //endregion
}
