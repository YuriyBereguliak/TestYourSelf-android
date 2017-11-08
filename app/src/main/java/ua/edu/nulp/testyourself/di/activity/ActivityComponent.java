package ua.edu.nulp.testyourself.di.activity;

import dagger.Component;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.di.application.AppComponent;
import ua.edu.nulp.testyourself.ui.activities.home.HomeActivity;
import ua.edu.nulp.testyourself.ui.activities.splash.SplashActivity;
import ua.edu.nulp.testyourself.ui.fragments.home.HomeFragment;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

    void inject(HomeActivity homeActivity);

    void inject(HomeFragment homeFragment);

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