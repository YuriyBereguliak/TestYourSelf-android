package ua.edu.nulp.testyourself.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.databinding.ItemUserBinding;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> mUsers;
    private OnUserClickListener mOnUserClickListener;

    public UsersAdapter(OnUserClickListener onUserClickListener) {
        mOnUserClickListener = onUserClickListener;
        mUsers = new ArrayList<>();
    }

    public void setUsers(List<User> users) {
        mUsers.clear();
        mUsers.addAll(users);
        notifyItemRangeChanged(0, mUsers.size());
    }

    //region RecyclerView
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        binding.setOnClickListener(mOnUserClickListener);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.mBinding.setUser(mUsers.get(position));
        holder.mBinding.setIsDividerVisible(true);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }
    //endregion

    //region RecyclerView.ViewHolder
    static class UserViewHolder extends RecyclerView.ViewHolder {

        private final ItemUserBinding mBinding;

        UserViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

    }
    //endregion

    //region Utility structures
    public interface OnUserClickListener {
        void onUserClickListener(User user);
    }
    //endregion
}
