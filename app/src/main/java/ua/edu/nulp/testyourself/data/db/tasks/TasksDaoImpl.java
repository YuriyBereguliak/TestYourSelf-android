package ua.edu.nulp.testyourself.data.db.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

import ua.edu.nulp.testyourself.model.Task;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface TasksDaoImpl extends TasksDao {

    @Override
    @NonNull
    @Query("SELECT * FROM Tasks ORDER BY RANDOM() LIMIT 1")
    LiveData<List<Task>> getAllTasks();
}
