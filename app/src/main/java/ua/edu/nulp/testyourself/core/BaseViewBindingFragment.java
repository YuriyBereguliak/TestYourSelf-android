package ua.edu.nulp.testyourself.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;

public abstract class BaseViewBindingFragment extends BaseFragment {

    private Unbinder mUnbinder;

    protected abstract Unbinder bindButterView(View view);

    //region BaseFragment
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mUnbinder = bindButterView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    //endregion
}
