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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private int mUserId;

    @ColumnInfo(name = "user_name")
    private String mUserName;

    @ColumnInfo(name = "user_avatar")
    private byte[] mUserAvatar;

    @ColumnInfo(name = "user_best_result")
    private String mBestResult;

    //region Getters and setters
    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

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

    public String getBestResult() {
        return mBestResult;
    }

    public void setBestResult(String bestResult) {
        mBestResult = bestResult;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "User{" +
                "mUserId=" + mUserId +
                ", mUserName='" + mUserName + '\'' +
                ", mUserAvatar=" + Arrays.toString(mUserAvatar) +
                ", mBestResult='" + mBestResult + '\'' +
                '}';
    }
    //endregion
}
