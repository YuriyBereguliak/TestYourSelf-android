package ua.edu.nulp.testyourself.data.db.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Dao
public interface TasksDaoImpl extends TasksDao {

    @Override
    @NonNull
    @Query("SELECT * FROM Tasks ORDER BY RANDOM() LIMIT 1")
    LiveData<List<TaskDetails>> getAllTasks();
}
