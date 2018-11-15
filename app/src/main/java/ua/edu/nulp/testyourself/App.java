package ua.edu.nulp.testyourself;

import android.app.Application;

import ua.edu.nulp.testyourself.di.application.AppComponent;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    //region Application
    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
        initDB();
    }
    //endregion

    //region Getters
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
    //endregion

    //region Utility API
    private void initDagger() {
        mAppComponent = AppComponent.Initializer.init(this);
        mAppComponent.inject(this);
    }

    private void initDB() {
        mAppComponent.tasksDataSource().initDatabase();
    }
    //endregion
}
