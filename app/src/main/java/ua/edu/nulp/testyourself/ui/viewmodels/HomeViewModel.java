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

public class HomeViewModel extends AndroidViewModel {

    private LifecycleOwner mLifecycleOwner;

    public HomeViewModel(Application application) {
        super(application);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    //region ViewModel
    public LiveData<List<User>> getUsers(UserDataSource userDataSource) {
        final MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
        userDataSource.getAllUsers().observe(mLifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                mutableLiveData.setValue(users);
            }
        });
        return mutableLiveData;
    }
    //endregion
}
