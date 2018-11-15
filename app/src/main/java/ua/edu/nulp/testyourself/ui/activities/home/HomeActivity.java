package ua.edu.nulp.testyourself.ui.activities.home;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.ui.activities.results.AllResultsActivity;
import ua.edu.nulp.testyourself.ui.activities.settings.SettingsActivity;
import ua.edu.nulp.testyourself.ui.activities.test.TestActivity;
import ua.edu.nulp.testyourself.ui.fragments.home.HomeFragment;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class HomeActivity extends BaseActivity implements HomeActivityNavigation {

    @BindView(R.id.framelayout_activity_home_main_container)
    FrameLayout mMainFragmentContainerFrameLayout;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    //region BaseActivity
    @Override
    protected int getContentViewID() {
        return R.layout.activity_home;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);

        showHomeFragment();
    }

    @Override
    protected void inject() {
        ActivityComponent.Initializer.init(mApp.getAppComponent(), this).inject(this);
    }
    //endregion

    //region HomeActivityNavigation
    @Override
    public void showHomeFragment() {
        addFragment(mMainFragmentContainerFrameLayout.getId(), HomeFragment.newInstance(), false);
    }

    @Override
    public void showSettingsActivity() {
        startActivity(SettingsActivity.getStartIntent(this));
    }

    @Override
    public void showAllResultsActivity() {
        startActivity(AllResultsActivity.getStartIntent(this));
    }

    @Override
    public void showTestActivity() {
        startActivity(TestActivity.getStartIntent(this));
    }
    //endregion
}
