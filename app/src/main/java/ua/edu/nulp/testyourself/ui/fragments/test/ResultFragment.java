package ua.edu.nulp.testyourself.ui.fragments.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.data.datasource.ResultDataSource;
import ua.edu.nulp.testyourself.databinding.FragmentResultBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.Result;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.model.handlers.OnActionClickListener;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 19.12.2017.
 */

public class ResultFragment extends BaseFragment implements OnActionClickListener {

    private static final String ARGUMENT_USER_RESULT = "ua.edu.nulp.testyourself.ui.fragments.test.USER_RESULT";
    private static final String ARGUMENT_USER_INSTANCE = "ua.edu.nulp.testyourself.ui.fragments.test.USER_INSTANCE";

    @Inject
    ResultDataSource mResultDataSource;

    private FragmentResultBinding mBinding;

    public static ResultFragment newInstance(User user, String result) {
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_USER_INSTANCE, user);
        args.putString(ARGUMENT_USER_RESULT, result);
        ResultFragment fragment = new ResultFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void bindViewModel() {
        mBinding.setOnHomeButtonClick(this);
        mBinding.setPercents(getArguments().getString(ARGUMENT_USER_RESULT, ""));

        Result result = new Result();
        result.setGameResult(getArguments().getString(ARGUMENT_USER_RESULT, ""));
        result.setUserName(((User) getArguments().getParcelable(ARGUMENT_USER_INSTANCE)).getUserName());
        mResultDataSource.insertData(result);
    }
    //endregion

    //region OnActionClickListener
    @Override
    public void onActionClick() {
        getActivity().finish();
    }
    //endregion
}
