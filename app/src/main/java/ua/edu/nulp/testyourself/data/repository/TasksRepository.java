package ua.edu.nulp.testyourself.data.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;
import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.data.db.tasks.TasksDao;
import ua.edu.nulp.testyourself.model.Choice;
import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.TaskDetails;
import ua.edu.nulp.testyourself.utils.L;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class TasksRepository implements TasksDataSource {

    private static final String FILE_NAME = "json_response.json";
    private static final String UTF_8 = "UTF-8";

    private Context mContext;
    private TasksDao mTasksDao;
    private ThreadExecutor mThreadExecutor;

    public TasksRepository(Context context, ThreadExecutor threadExecutor, TasksDao tasksDao) {
        mContext = context;
        mTasksDao = tasksDao;
        mThreadExecutor = threadExecutor;
    }

    //region TasksDataSource
    @Override
    public void initDatabase() {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String json = loadJSONFromAsset();
                if (TextUtils.isEmpty(json)) {
                    L.e("Json file is empty");
                    return;
                }

                List<TaskDetails> taskDetails = new Gson().fromJson(json, new TypeToken<List<TaskDetails>>() {
                }.getType());

                for (TaskDetails details : taskDetails) {
                    mTasksDao.insert(details.mTask);
                    mTasksDao.insertAllChoices(details.mChoices);
                }
            }
        });
    }

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

    //region Utility API
    @Nullable
    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream inputStream = mContext.getAssets().open(FILE_NAME);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, UTF_8);
        } catch (IOException ex) {
            L.e(ex.toString());
            return null;
        }
        return json;
    }
    //endregion
}
