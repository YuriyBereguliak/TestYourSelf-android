package ua.edu.nulp.testyourself.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ua.edu.nulp.testyourself.App;
import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;
import ua.edu.nulp.testyourself.data.datasource.ResultDataSource;
import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.data.datasource.UserDataSource;
import ua.edu.nulp.testyourself.di.database.DatabaseModule;

@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class})
public interface AppComponent {

    //region Initializer
    final class Initializer {

        private Initializer() {
        }

        public static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .databaseModule(new DatabaseModule(app))
                    .build();
        }
    }
    //endregion

    void inject(App app);

    Context context();

    ThreadExecutor threadExecutor();

    UserDataSource userDataSource();

    ResultDataSource resultDataSource();

    TasksDataSource tasksDataSource();
}
