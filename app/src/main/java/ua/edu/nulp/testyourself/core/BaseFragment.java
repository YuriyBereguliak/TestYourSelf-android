package ua.edu.nulp.testyourself.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.edu.nulp.testyourself.App;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.utils.L;

public abstract class BaseFragment extends LifecycleFragment implements Handler.Callback {

    private static final int MSG_SHOW_LOADING_DIALOG = 0x1000;
    private static final int MSG_UPDATE_LOADING_MESSAGE = 0x1001;
    private static final int MSG_HIDE_LOADING_DIALOG = 0x1002;

    protected App mApp;
    protected NavigationActivityComponentProvider mNavigationActivityComponentProvider;
    protected Handler mUIHandler = new Handler(BaseFragment.this);
    private ProgressDialog mProgressDialog;

    public BaseFragment() {
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    protected abstract void inject();

    protected abstract View bindView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void bindViewModel();

    //region Fragment
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mApp = (App) activity.getApplication();

        if (activity instanceof NavigationActivityComponentProvider) {
            try {
                mNavigationActivityComponentProvider = (NavigationActivityComponentProvider) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }

        if (!(activity instanceof BaseActivity)) {
            L.e("Activity should be instanceof BaseActivity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return bindView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindViewModel();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //endregion

    //region Handler.Callback
    @Override
    public boolean handleMessage(Message message) {
        boolean result = false;
        switch (message.what) {
            case MSG_SHOW_LOADING_DIALOG:
                result = handleShowLoadingDialog(message);
                break;
            case MSG_UPDATE_LOADING_MESSAGE:
                result = handleUpdateLoadingDialog(message);
                break;
            case MSG_HIDE_LOADING_DIALOG:
                result = handleHideLoadingDialog();
                break;
        }
        return result;
    }
    //endregion

    public boolean onBackPressed() {
        return false;
    }

    public void showLoadingDialog(String messageValue) {
        Message message = new Message();
        message.what = MSG_SHOW_LOADING_DIALOG;
        message.obj = messageValue;
        mUIHandler.sendMessage(message);
    }

    public void updateLoadingDialogMessage(String messageValue) {
        Message message = new Message();
        message.what = MSG_UPDATE_LOADING_MESSAGE;
        message.obj = messageValue;
        mUIHandler.sendMessage(message);
    }

    public void hideLoadingDialog() {
        Message message = new Message();
        message.what = MSG_HIDE_LOADING_DIALOG;
        mUIHandler.sendMessage(message);
    }

    //region ErrorUiHandler
    public void showError(final String title, final String message) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(getActivity())
                            .setTitle(title)
                            .setMessage(message)
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                }
            });
        } else {
            L.e("Activity is detached.");
        }
    }
    //endregion

    //region Utility API
    private boolean isProgressShowing() {
        return (mProgressDialog != null) && mProgressDialog.isShowing();
    }

    private boolean handleHideLoadingDialog() {
        if (isProgressShowing()) {
            mProgressDialog.dismiss();
        }
        return true;
    }

    private boolean handleUpdateLoadingDialog(Message message) {
        if (isProgressShowing()) {
            mProgressDialog.setMessage((String) message.obj);
        }
        return true;
    }

    private boolean handleShowLoadingDialog(Message message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }
        mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    dialogInterface.dismiss();
                    return true;
                }
                return false;
            }
        });
        mProgressDialog.setMessage((String) message.obj);
        mProgressDialog.setCancelable(false);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
        return true;
    }
    //endregion
}