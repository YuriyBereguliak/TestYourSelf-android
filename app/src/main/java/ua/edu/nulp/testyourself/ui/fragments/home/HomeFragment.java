package ua.edu.nulp.testyourself.ui.fragments.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.databinding.FragmentHomeBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.ui.activities.home.HomeActivityNavigation;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class HomeFragment extends BaseFragment {

    @Inject
    HomeActivityNavigation mHomeActivityNavigation;

    private FragmentHomeBinding mBinding;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region BaseFragment
    @Override
    protected void inject() {
        ActivityComponent.Initializer.init(mApp.getAppComponent(), (BaseActivity) getActivity()).inject(this);
    }

    @Override
    protected View bindView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void bindViewModel() {
        ButterKnife.bind(this, mBinding.getRoot());
    }
    //endregion

    //region Click handlers
    @OnClick(R.id.imageview_fragment_home_results)
    void onAllResultsClickListener() {
        mHomeActivityNavigation.showAllResultsActivity();
    }

    @OnClick(R.id.imageview_fragment_home_settings)
    void onSettingsClickListener() {
        mHomeActivityNavigation.showSettingsActivity();
    }

    @OnClick(R.id.framelayout_fragment_home_start_test)
    void onStartTestClickListener() {
        mHomeActivityNavigation.showTestActivity();
    }
    //endregion
}
