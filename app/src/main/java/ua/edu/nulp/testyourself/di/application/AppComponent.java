package ua.edu.nulp.testyourself.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ua.edu.nulp.testyourself.App;
import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    //region Initializer
    final class Initializer {

        private Initializer() {
        }

        public static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
    //endregion

    void inject(App app);

    Context context();

    ThreadExecutor threadExecutor();
}
