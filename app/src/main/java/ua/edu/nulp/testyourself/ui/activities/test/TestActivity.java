package ua.edu.nulp.testyourself.ui.activities.test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.ui.fragments.test.ResultFragment;
import ua.edu.nulp.testyourself.ui.fragments.test.TestFragment;
import ua.edu.nulp.testyourself.ui.fragments.test.UserNameFragment;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class TestActivity extends BaseActivity implements TestActivityNavigation {

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, TestActivity.class);
    }

    //region BaseActivity
    @Override
    protected int getContentViewID() {
        return R.layout.activity_test;
    }

    @Override
    protected void bindView() {
        showUserNameFragment();
    }

    @Override
    protected void inject() {
        ActivityComponent.Initializer.init(mApp.getAppComponent(), this).inject(this);
    }
    //endregion

    //region TestActivityNavigation
    @Override
    public void showUserNameFragment() {
        replaceFragment(getFrameLayoutId(), UserNameFragment.newInstance(), false);
    }

    @Override
    public void showTestFragment(User user) {
        replaceFragment(getFrameLayoutId(), TestFragment.newInstance(user), true, true);
    }

    @Override
    public void showResultFragment(User user, String result) {
        replaceFragment(getFrameLayoutId(), ResultFragment.newInstance(user, result), true, true);
    }
    //endregion

    //region Utility API
    private int getFrameLayoutId() {
        return R.id.framelayout_activity_test;
    }
    //endregion
}
