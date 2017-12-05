package ua.edu.nulp.testyourself.ui.activities.results;

import android.content.Context;
import android.content.Intent;

import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.ui.fragments.results.AllResultsFragment;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class AllResultsActivity extends BaseActivity implements AllResultsActivityNavigation {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, AllResultsActivity.class);
    }

    //region BaseActivity
    @Override
    protected int getContentViewID() {
        return R.layout.activity_all_results;
    }

    @Override
    protected void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void inject() {
        ActivityComponent.Initializer.init(mApp.getAppComponent(), this).inject(this);
    }
    //endregion

    //region AllResultsActivityNavigation
    @Override
    public void showAllResultsFragment() {
        replaceFragment(getFragmentContainerId(), AllResultsFragment.newInstance(), false);
    }
    //endregion

    //region Utility API
    private int getFragmentContainerId() {
        return R.id.framelayout_activity_all_results;
    }
    //endregion
}
