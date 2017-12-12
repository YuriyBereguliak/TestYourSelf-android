package ua.edu.nulp.testyourself.data.datasource;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface TasksDataSource {
    LiveData<List<TaskDetails>> getAllTasks();
}
