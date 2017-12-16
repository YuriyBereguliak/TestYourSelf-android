package ua.edu.nulp.testyourself.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 16.12.2017.
 */

public class TestViewModel extends AndroidViewModel {

    private LifecycleOwner mLifecycleOwner;

    public TestViewModel(Application application) {
        super(application);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }


}
