package ua.edu.nulp.testyourself.ui.fragments.test;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.databinding.FragmentTextBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.ui.viewmodels.TestViewModel;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 16.12.2017.
 */

public class TestFragment extends BaseFragment implements OnCancelTestClickListener {

    private static final String ARGUMENT_USER = "ua.edu.nulp.testyourself.ui.fragments.test.USER";

    private TestViewModel mTestViewModel;
    private FragmentTextBinding mBinding;

    public static TestFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_USER, user);
        TestFragment fragment = new TestFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_text, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void bindViewModel() {
        ButterKnife.bind(this, mBinding.getRoot());
        initViewModel();
        initBindingView();
    }
    //endregion

    //region OnCancelTestClickListener
    @Override
    public void onCancelTest() {

    }
    //endregion

    //region Utility API
    private void initBindingView() {
        mBinding.setUser((User) getArguments().getParcelable(ARGUMENT_USER));
        mBinding.setOnCancelTestClick(this);
    }

    private void initViewModel() {
        mTestViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mTestViewModel.setLifecycleOwner(this);

        mBinding.setViewModel(mTestViewModel);
    }
    //endregion

}
