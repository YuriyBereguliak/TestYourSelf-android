package ua.edu.nulp.testyourself.di.database;

import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.edu.nulp.testyourself.App;
import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;
import ua.edu.nulp.testyourself.data.datasource.ResultDataSource;
import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.data.datasource.UserDataSource;
import ua.edu.nulp.testyourself.data.db.AppDatabase;
import ua.edu.nulp.testyourself.data.db.result.ResultDao;
import ua.edu.nulp.testyourself.data.db.result.ResultDaoImpl;
import ua.edu.nulp.testyourself.data.db.tasks.TasksDao;
import ua.edu.nulp.testyourself.data.db.tasks.TasksDaoImpl;
import ua.edu.nulp.testyourself.data.db.user.UserDao;
import ua.edu.nulp.testyourself.data.db.user.UserDaoImpl;
import ua.edu.nulp.testyourself.data.repository.ResultRepository;
import ua.edu.nulp.testyourself.data.repository.TasksRepository;
import ua.edu.nulp.testyourself.data.repository.UserRepository;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Module
public class DatabaseModule {

    private static final String MONEY_CACHE_DB = "TestYourSelf.db";

    private AppDatabase mAppDatabase;

    public DatabaseModule(App app) {
        mAppDatabase = Room.databaseBuilder(app.getApplicationContext(),
                AppDatabase.class,
                MONEY_CACHE_DB)
                .build();
    }

    @Provides
    @Singleton
    UserDaoImpl provideUserDao() {
        return mAppDatabase.getUserDao();
    }

    @Provides
    @Singleton
    TasksDaoImpl provideTasksDao() {
        return mAppDatabase.getTasksDao();
    }

    @Provides
    @Singleton
    ResultDaoImpl provideResultDao() {
        return mAppDatabase.getResultDao();
    }

    @Provides
    @Singleton
    UserDataSource provideUserDataSource(ThreadExecutor threadExecutor, UserDaoImpl userDao) {
        return new UserRepository(threadExecutor, userDao);
    }

    @Provides
    @Singleton
    TasksDataSource provideTasksRepository(TasksDaoImpl tasksDao) {
        return new TasksRepository(tasksDao);
    }

    @Provides
    @Singleton
    ResultDataSource provideResultDataSource(ThreadExecutor threadExecutor, ResultDaoImpl resultDao) {
        return new ResultRepository(threadExecutor, resultDao);
    }
}
