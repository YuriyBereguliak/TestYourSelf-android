package ua.edu.nulp.testyourself.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
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
        if (users == null) {
            return;
        }

        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    //region RecyclerView
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bindView(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
    //endregion

    //region Utility structures
    static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView mTextView;

        UserViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindView(User user) {
            mTextView.setText(user.getUserName());
        }
    }

    public interface OnUserClickListener {
        void onUserClickListener(User user);
    }
    //endregion
}
