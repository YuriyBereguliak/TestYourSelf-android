package ua.edu.nulp.testyourself.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.data.db.tasks.TasksDao;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class TasksRepository implements TasksDataSource {

    private TasksDao mTasksDao;

    public TasksRepository(TasksDao tasksDao) {
        mTasksDao = tasksDao;
    }

    //region TasksDataSource
    @Override
    public LiveData<List<TaskDetails>> getAllTasks() {
        return mTasksDao.getAllTasks();
    }
    //endregion
}
