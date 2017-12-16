package ua.edu.nulp.testyourself.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;
import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.data.db.tasks.TasksDao;
import ua.edu.nulp.testyourself.model.Choice;
import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class TasksRepository implements TasksDataSource {

    private TasksDao mTasksDao;
    private ThreadExecutor mThreadExecutor;

    public TasksRepository(ThreadExecutor threadExecutor, TasksDao tasksDao) {
        mTasksDao = tasksDao;
        mThreadExecutor = threadExecutor;
    }

    //region TasksDataSource
    @Override
    public LiveData<List<TaskDetails>> getAllTasks() {
        return mTasksDao.getAllTasks();
    }

    @Override
    public void insert(final Task task) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTasksDao.insert(task);
            }
        });
    }

    @Override
    public void insertAllTasks(final List<Task> tasks) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTasksDao.insertAllTasks(tasks);
            }
        });
    }

    @Override
    public void insert(final Choice choice) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTasksDao.insert(choice);
            }
        });
    }

    @Override
    public void insertAllChoices(final List<Choice> choices) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTasksDao.insertAllChoices(choices);
            }
        });
    }
    //endregion
}
