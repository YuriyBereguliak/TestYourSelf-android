package ua.edu.nulp.testyourself.di.activity;

import dagger.Component;
import ua.edu.nulp.testyourself.MainActivity;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.di.application.AppComponent;

@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    //region Builder
    final class Initializer {

        private Initializer() {

        }

        public static ActivityComponent init(AppComponent appComponent, BaseActivity activity) {
            return DaggerActivityComponent.builder()
                    .appComponent(appComponent)
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }
    //endregion

}
