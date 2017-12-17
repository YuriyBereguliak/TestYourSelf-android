package ua.edu.nulp.testyourself;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ua.edu.nulp.testyourself.di.application.AppComponent;
import ua.edu.nulp.testyourself.model.TaskDetails;
import ua.edu.nulp.testyourself.utils.L;

/**
 * TestYourSelf project
 * Created by Yuriy Bereguliak on 08.11.2017.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    //region Application
    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
        initDB();
    }
    //endregion

    //region Getters
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
    //endregion

    //region Utility API
    private void initDagger() {
        mAppComponent = AppComponent.Initializer.init(this);
        mAppComponent.inject(this);
    }

    private void initDB() {
        mAppComponent.threadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<TaskDetails> taskDetails = new Gson().fromJson(loadJSONFromAsset(), new TypeToken<List<TaskDetails>>() {
                }.getType());

                L.d(taskDetails.toString());
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("json_response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //endregion
}
