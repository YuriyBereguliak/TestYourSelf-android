package ua.edu.nulp.testyourself.di.activity;

import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.ui.activities.home.HomeActivityNavigation;
import ua.edu.nulp.testyourself.ui.activities.results.AllResultsActivityNavigation;
import ua.edu.nulp.testyourself.ui.activities.test.TestActivityNavigation;
import ua.edu.nulp.testyourself.utils.L;

@Module
class ActivityModule {

    private BaseActivity mBaseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    @Nullable
    @Provides
    BaseActivity provideActivity() {
        return mBaseActivity;
    }

    @Provides
    HomeActivityNavigation provideHomeActivityNavigation() {
        if (mBaseActivity instanceof HomeActivityNavigation) {
            return (HomeActivityNavigation) mBaseActivity;
        }

        L.e("Activity not instance of HomeActivityNavigation");
        return null;
    }

    @Provides
    AllResultsActivityNavigation provideAllResultsActivityNavigation() {
        if (mBaseActivity instanceof AllResultsActivityNavigation) {
            return (AllResultsActivityNavigation) mBaseActivity;
        }

        L.e("Activity not instance of AllResultsActivityNavigation");
        return null;
    }

    @Provides
    TestActivityNavigation provideTestActivityNavigation(){
        if (mBaseActivity instanceof TestActivityNavigation) {
            return (TestActivityNavigation) mBaseActivity;
        }

        L.e("Activity not instance of TestActivityNavigation");
        return null;
    }
}
