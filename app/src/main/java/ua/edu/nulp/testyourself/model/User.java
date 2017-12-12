package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Users")
public class User {

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
