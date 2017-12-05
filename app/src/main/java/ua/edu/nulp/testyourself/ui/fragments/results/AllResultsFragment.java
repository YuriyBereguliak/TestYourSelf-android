package ua.edu.nulp.testyourself.ui.fragments.results;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.databinding.FragmentHomeBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.ui.activities.results.AllResultsActivityNavigation;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class AllResultsFragment extends BaseFragment {

    @Inject
    AllResultsActivityNavigation mAllResultsActivityNavigation;

    public static AllResultsFragment newInstance() {
        Bundle args = new Bundle();
        AllResultsFragment fragment = new AllResultsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region BaseFragment
    @Override
    protected void inject() {
        ActivityComponent.Initializer.init(mApp.getAppComponent(), ((BaseActivity) getActivity())).inject(this);
    }

    @Override
    protected View bindView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_results, container, false);
        return binding.getRoot();
    }

    @Override
    protected void bindViewModel() {
    }
    //endregion
}
