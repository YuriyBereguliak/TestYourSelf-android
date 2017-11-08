package ua.edu.nulp.testyourself.di.activity;

import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;
import ua.edu.nulp.testyourself.core.BaseActivity;

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

}
