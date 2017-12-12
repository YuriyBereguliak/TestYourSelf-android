package ua.edu.nulp.testyourself.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ua.edu.nulp.testyourself.data.db.result.ResultDaoImpl;
import ua.edu.nulp.testyourself.data.db.tasks.TasksDaoImpl;
import ua.edu.nulp.testyourself.data.db.user.UserDaoImpl;
import ua.edu.nulp.testyourself.model.Choice;
import ua.edu.nulp.testyourself.model.Result;
import ua.edu.nulp.testyourself.model.Task;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Database(entities = {User.class, Result.class, Choice.class, Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDaoImpl getUserDao();

    public abstract ResultDaoImpl getResultDao();

    public abstract TasksDaoImpl getTasksDao();
}
