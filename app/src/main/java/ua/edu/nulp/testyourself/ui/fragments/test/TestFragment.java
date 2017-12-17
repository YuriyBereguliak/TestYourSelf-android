package ua.edu.nulp.testyourself.ui.fragments.test;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.core.BaseFragment;
import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.databinding.FragmentTextBinding;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.model.TaskDetails;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.ui.adapters.TestAdapter;
import ua.edu.nulp.testyourself.ui.viewmodels.TestViewModel;
import ua.edu.nulp.testyourself.utils.L;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 16.12.2017.
 */

public class TestFragment extends BaseFragment implements OnCancelTestClickListener {

    private static final String ARGUMENT_USER = "ua.edu.nulp.testyourself.ui.fragments.test.USER";

    @Inject
    TasksDataSource mTasksDataSource;

    private TestAdapter mAdapter;
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
        initAdapter();
        return mBinding.getRoot();
    }

    @Override
    protected void bindViewModel() {
        ButterKnife.bind(this, mBinding.getRoot());
        initViewModel();
        initBindingView();
        loadTestData();
    }

    @Override
    public boolean onBackPressed() {
        initFinishTestDialog();
        return true;
    }
    //endregion

    //region OnCancelTestClickListener
    @Override
    public void onCancelTest() {
        initFinishTestDialog();
    }
    //endregion

    //region Utility API
    private void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new TestAdapter();
        }
        mBinding.recyclerviewFragmentTestQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerviewFragmentTestQuestions.setAdapter(mAdapter);
    }

    private void initBindingView() {
        mBinding.setUser((User) getArguments().getParcelable(ARGUMENT_USER));
        mBinding.setOnCancelTestClick(this);
    }

    private void initViewModel() {
        mTestViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mTestViewModel.setLifecycleOwner(this);

        mBinding.setViewModel(mTestViewModel);
    }

    private void loadTestData() {
        mTestViewModel.loadTasks(mTasksDataSource)
                .observe(this, new Observer<List<TaskDetails>>() {
                    @Override
                    public void onChanged(@Nullable List<TaskDetails> taskDetails) {
                        if (taskDetails == null) {
                            L.e("Task details == null");
                            return;
                        }

                        L.d(taskDetails.toString());
                        mAdapter.setTaskDetails(taskDetails);

                        mBinding.setIsShowRecyclerView(true);
                    }
                });
    }

    private void initFinishTestDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.text_fragment_test_attention_title)
                .setMessage(R.string.test_fragment_test_dialog_message)
                .setPositiveButton(R.string.test_fragment_test_positive_button_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.text_fragment_test_negative_button_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
    //endregion

}
