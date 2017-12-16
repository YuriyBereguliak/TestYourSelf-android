package ua.edu.nulp.testyourself.data.db.tasks;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ua.edu.nulp.testyourself.model.Choice;
import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Dao
public interface TasksDaoImpl extends TasksDao {

    @Override
    @Query("SELECT * FROM Tasks ORDER BY RANDOM() LIMIT 10")
    LiveData<List<TaskDetails>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTasks(List<Task> tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Choice choice);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChoices(List<Choice> choices);
}
