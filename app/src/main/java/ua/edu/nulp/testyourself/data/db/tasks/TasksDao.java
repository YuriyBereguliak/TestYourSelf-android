package ua.edu.nulp.testyourself.data.db.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;

import java.util.List;

import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface TasksDao {

    LiveData<List<TaskDetails>> getAllTasks();
}
