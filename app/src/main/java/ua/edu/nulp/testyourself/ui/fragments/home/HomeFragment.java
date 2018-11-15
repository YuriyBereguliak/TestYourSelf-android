package ua.edu.nulp.testyourself.ui.fragments.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.data.datasource.UserDataSource;
import ua.edu.nulp.testyourself.databinding.FragmentHomeBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.ui.activities.home.HomeActivityNavigation;
import ua.edu.nulp.testyourself.ui.viewmodels.HomeViewModel;
import ua.edu.nulp.testyourself.utils.L;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class HomeFragment extends BaseFragment {

    @Inject
    HomeActivityNavigation mHomeActivityNavigation;

    @Inject
    UserDataSource mUserDataSource;

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

        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.setLifecycleOwner(this);

        loadUsers(homeViewModel);
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

    //region Utility API
    private void loadUsers(HomeViewModel homeViewModel) {
        homeViewModel.getUsers(mUserDataSource).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users == null) {
                    L.e("Empty result");
                    return;
                }

                L.d(users.toString());
            }
        });
    }
    //endregion
}
