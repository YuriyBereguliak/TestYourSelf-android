package ua.edu.nulp.testyourself.di.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.edu.nulp.testyourself.App;
import ua.edu.nulp.testyourself.core.executor.JobExecutor;
import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;

@Module
class AppModule {

    private final App mApp;

    AppModule(App app) {
        mApp = app;
    }

    @Provides
    Application provideApplication() {
        return mApp;
    }

    @Provides
    Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }
}
