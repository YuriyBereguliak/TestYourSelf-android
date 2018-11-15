package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Users")
public class User implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "user_name")
    private String mUserName;

    @ColumnInfo(name = "user_avatar")
    private byte[] mUserAvatar;

    public User(String userName) {
        mUserName = userName;
    }

    //region Getters and setters
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public byte[] getUserAvatar() {
        return mUserAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        mUserAvatar = userAvatar;
    }
    //endregion


    //region Parcelable
    protected User(Parcel in) {
        mUserName = in.readString();
        mUserAvatar = in.createByteArray();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @NonNull
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserName);
        dest.writeByteArray(mUserAvatar);
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "User{" +
                ", mUserName='" + mUserName + '\'' +
                ", mUserAvatar=" + Arrays.toString(mUserAvatar) +
                '}';
    }
    //endregion
}
