package ua.edu.nulp.testyourself.core;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.greysonparrelli.permiso.Permiso;

import ua.edu.nulp.testyourself.App;
import ua.edu.nulp.testyourself.R;

public abstract class BaseActivity extends LifecycleAppBarActivity
        implements android.os.Handler.Callback, LoadingUIHandler {

    private static final int MSG_SHOW_LOADING_DIALOG = 0x1000;      //4096 in dec
    private static final int MSG_UPDATE_LOADING_MESSAGE = 0x1001;   //4097 in dec
    private static final int MSG_HIDE_LOADING_DIALOG = 0x1002;      //4098 in dec

    protected App mApp;

    protected final android.os.Handler mUIHandler = new android.os.Handler(BaseActivity.this);

    private ProgressDialog mProgressDialog;

    protected abstract int getContentViewID();

    protected abstract void bindView();

    protected abstract void inject();

    //region AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Permiso.getInstance().setActivity(this);
        mApp = (App) getApplication();
        inject();
        setContentView(getContentViewID());
        bindView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Permiso.getInstance().setActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Permiso.getInstance().setActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        slideTopTransAnimation();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        slideTopTransAnimation();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        slideTopTransAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        slideTopTransAnimation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        slideBottomReverseTransAnimation();
    }

    @Override
    public void finish() {
        super.finish();
        slideBottomReverseTransAnimation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Permiso.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults);
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

    //region FragmentNavigation
    public void addFragment(int containerViewId, LifecycleFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void addFragment(int containerViewId, LifecycleFragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public <T extends Fragment> boolean isFragmentExist(Class<T> fragmentClass) {
        Fragment myFragment = getSupportFragmentManager()
                .findFragmentByTag(fragmentClass.getName());
        return myFragment != null;
    }

    public void replaceFragment(int containerViewId, LifecycleFragment fragment, boolean addToBackStack) {
        replaceFragment(containerViewId, fragment, addToBackStack, false);
    }


    public void replaceFragment(int containerViewId, LifecycleFragment fragment, boolean addToBackStack, boolean withAnimation) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        fragmentTransaction.replace(containerViewId, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void removeFragment(LifecycleFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    public <T extends Fragment> void removeFragment(Class<T> fragmentClass) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(fragmentClass.getName());
        if (fragmentByTag != null) {
            fragmentTransaction.remove(fragmentByTag)
                    .commitAllowingStateLoss();
        }
    }

    public void removeFragment(String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(getSupportFragmentManager().findFragmentByTag(tag)).commitAllowingStateLoss();
    }
    //endregion

    //region LoadingUiHandler
    @Override
    public void showLoadingDialog(String messageValue) {
        Message message = new Message();
        message.what = MSG_SHOW_LOADING_DIALOG;
        message.obj = messageValue;
        mUIHandler.sendMessage(message);
    }

    @Override
    public void updateLoadingDialogMessage(String messageValue) {
        Message message = new Message();
        message.what = MSG_UPDATE_LOADING_MESSAGE;
        message.obj = messageValue;
        mUIHandler.sendMessage(message);
    }

    @Override
    public void hideLoadingDialog() {
        Message message = new Message();
        message.what = MSG_HIDE_LOADING_DIALOG;
        mUIHandler.sendMessage(message);
    }
    //endregion

    //region Utility API
    private boolean isProgressShowing() {
        return (null != mProgressDialog) && mProgressDialog.isShowing();
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
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(BaseActivity.this);
        }
        mProgressDialog.setMessage((String) message.obj);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
        return true;
    }

    private void slideTopTransAnimation() {
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
    }

    private void slideBottomReverseTransAnimation() {
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }
    //endregion
}