package ua.edu.nulp.testyourself.ui.fragments.results;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.data.datasource.ResultDataSource;
import ua.edu.nulp.testyourself.databinding.FragmentAllResultsBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.Result;
import ua.edu.nulp.testyourself.ui.activities.results.AllResultsActivityNavigation;
import ua.edu.nulp.testyourself.ui.adapters.UserResultAdapter;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class AllResultsFragment extends BaseFragment {

    @Inject
    ResultDataSource mResultDataSource;

    @Inject
    AllResultsActivityNavigation mAllResultsActivityNavigation;

    private FragmentAllResultsBinding mBinding;

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_results, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void bindViewModel() {

        final UserResultAdapter userResultAdapter = new UserResultAdapter();

        mBinding.recyclerviewFragmentAllResults.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerviewFragmentAllResults.setAdapter(userResultAdapter);

        mResultDataSource.getAllResults().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                userResultAdapter.setResults(results);
            }
        });
    }
    //endregion
}
