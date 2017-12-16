package ua.edu.nulp.testyourself.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.model.User;
import ua.edu.nulp.testyourself.utils.L;

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
        holder.bindView(mUsers.get(position), mOnUserClickListener);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
    //endregion

    //region RecyclerView.ViewHolder
    static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linearlayout_fragment_home_action_button_container)
        LinearLayout mUserContainerLinearLayout;

        @BindView(R.id.imageview_item_user_avatar)
        ImageView mUserAvatarImageView;

        @BindView(R.id.textview_item_user_username)
        TextView mTextView;

        UserViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindView(final User user, final OnUserClickListener onUserClickListener) {

            mUserContainerLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onUserClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        onUserClickListener.onUserClickListener(user);
                    } else {
                        L.e("Can`t set user details");
                    }
                }
            });

            mTextView.setText(user.getUserName());
            Glide.with(mUserAvatarImageView)
                    .load(user.getUserAvatar())
                    .into(mUserAvatarImageView);
        }
    }
    //endregion

    //region Utility structures
    public interface OnUserClickListener {
        void onUserClickListener(User user);
    }
    //endregion
}
