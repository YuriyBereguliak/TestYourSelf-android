package ua.edu.nulp.testyourself.core;

import android.arch.lifecycle.LifecycleFragment;

public interface NavigationActivityComponentProvider {

    void replaceFragment(LifecycleFragment fragment, boolean addToBackStack);

    void setToolbarTitle(String title);

    void openFilterMenu();
}
