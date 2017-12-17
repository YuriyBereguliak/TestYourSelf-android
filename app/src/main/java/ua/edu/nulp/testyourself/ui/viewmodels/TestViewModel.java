package ua.edu.nulp.testyourself.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 16.12.2017.
 */

public class TestViewModel extends AndroidViewModel {

    private LifecycleOwner mLifecycleOwner;

    public TestViewModel(Application application) {
        super(application);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    //region TestViewModel
    public LiveData<List<TaskDetails>> loadTasks(TasksDataSource tasksDataSource) {
        final MutableLiveData<List<TaskDetails>> liveData = new MutableLiveData<>();
        tasksDataSource.getAllTasks().observe(mLifecycleOwner, new Observer<List<TaskDetails>>() {
            @Override
            public void onChanged(@Nullable List<TaskDetails> taskDetails) {
                liveData.postValue(taskDetails);
            }
        });
        return liveData;
    }
    //endregion
}
