package ua.edu.nulp.testyourself.ui.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.edu.nulp.testyourself.R;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/5/17.
 */

public class SettingsActivity extends PreferenceActivity {

    @NonNull
    public static Intent getStartIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    //region PreferenceActivity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_settings);
    }
    //endregion
}
