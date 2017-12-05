package ua.edu.nulp.testyourself.ui.fragments.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.databinding.FragmentUserNameBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class UserNameFragment extends BaseFragment {

    private FragmentUserNameBinding mBinding;

    public static UserNameFragment newInstance() {
        Bundle args = new Bundle();
        UserNameFragment fragment = new UserNameFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_name, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void bindViewModel() {
        ButterKnife.bind(this, mBinding.getRoot());
    }
    //endregion
}
