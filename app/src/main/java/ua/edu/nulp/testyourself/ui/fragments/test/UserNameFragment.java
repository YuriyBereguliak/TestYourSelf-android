package ua.edu.nulp.testyourself.ui.fragments.test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.data.datasource.UserDataSource;
import ua.edu.nulp.testyourself.databinding.FragmentUserNameBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.ui.activities.test.TestActivityNavigation;
import ua.edu.nulp.testyourself.ui.viewmodels.UserNameViewModel;
import ua.edu.nulp.testyourself.utils.L;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class UserNameFragment extends BaseFragment {

    @BindView(R.id.swipelayout_fargment_user_name)
    SwipeLayout mSwipeLayout;

    @BindView(R.id.textinputedittext_fragment_user_name)
    TextInputEditText mUserNameTextInputEditText;

    @Inject
    UserDataSource mUserDataSource;

    @Inject
    TestActivityNavigation mTestActivityNavigation;

    private UserNameViewModel mUserNameViewModel;
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

        mUserNameViewModel = ViewModelProviders.of(this).get(UserNameViewModel.class);
        mUserNameViewModel.setLifecycleOwner(this);

        loadAllData();
    }
    //endregion

    //region Click handlers
    @OnClick(R.id.circleimageview_fragment_user_name_avatar)
    void chooseAvatarClickListener() {

    }

    @OnClick(R.id.textview_fragment_user_name_start_game_and_register)
    void startGameClickListener() {
        mUserNameViewModel.saveUser(mUserDataSource, new User(mUserNameTextInputEditText.getText().toString()))
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean == Boolean.TRUE) {
                            TastyToast.makeText(getContext(), getString(R.string.text_fragment_user_name_account_created),
                                    TastyToast.LENGTH_SHORT,
                                    TastyToast.SUCCESS).show();

                            mTestActivityNavigation.showTestFragment();
                        } else {
                            TastyToast.makeText(getContext(), getString(R.string.text_fragment_user_name_user_already_exist),
                                    TastyToast.LENGTH_SHORT,
                                    TastyToast.ERROR).show();
                        }
                    }
                });
    }
    //endregion

    //region Utility API
    private void loadAllData() {
        mUserNameViewModel.getAllUsers(mUserDataSource).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users == null) {
                    L.e("Result == null");
                    return;
                }

                L.d(users.toString());
            }
        });
    }
    //endregion
}
