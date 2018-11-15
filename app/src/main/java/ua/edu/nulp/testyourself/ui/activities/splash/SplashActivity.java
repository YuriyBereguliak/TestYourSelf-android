package ua.edu.nulp.testyourself.ui.activities.splash;

import android.os.Message;

import javax.inject.Inject;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.core.BaseActivity;
import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.di.activity.ActivityComponent;
import ua.edu.nulp.testyourself.ui.activities.home.HomeActivity;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class SplashActivity extends BaseActivity {

    private static final long DELAY_SEND_MESSAGE_LOADING_MILLIS = 1000L;
    private static final int MSG_GET_ACTIVE_USER = 0x1003;                  //4099 in dec

    @Inject
    TasksDataSource mTasksDataSource;

    //region BaseActivity
    @Override
    protected int getContentViewID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void bindView() {
        sendLoadingMessage();
    }

    @Override
    protected void inject() {
        ActivityComponent.Initializer.init(mApp.getAppComponent(), this).inject(this);
    }

    @Override
    public boolean handleMessage(Message message) {
        boolean result;
        switch (message.what) {
            case MSG_GET_ACTIVE_USER:
                result = true;
                startMainActivity();
                break;
            default:
                result = super.handleMessage(message);
        }
        return result;
    }
    //endregion

    //region Utility API
    private void sendLoadingMessage() {
        mUIHandler.removeMessages(MSG_GET_ACTIVE_USER);
        mUIHandler.sendMessageDelayed(Message.obtain(mUIHandler, MSG_GET_ACTIVE_USER), DELAY_SEND_MESSAGE_LOADING_MILLIS);
    }

    private void startMainActivity() {
        startActivity(HomeActivity.getStartIntent(SplashActivity.this));
        finish();
    }
    //endregion
}
