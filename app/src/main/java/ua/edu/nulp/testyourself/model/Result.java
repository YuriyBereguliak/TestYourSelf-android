package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Results",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class Result {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "result_id")
    private int mResultId;

    @ColumnInfo(name = "user_id")
    private int mUserId;

    @ColumnInfo(name = "result")
    private String mGameResult;

    //region Getters and Setters
    public int getResultId() {
        return mResultId;
    }

    public void setResultId(int resultId) {
        mResultId = resultId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getGameResult() {
        return mGameResult;
    }

    public void setGameResult(String gameResult) {
        mGameResult = gameResult;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "Result{" +
                "mResultId=" + mResultId +
                ", mUserId=" + mUserId +
                ", mGameResult='" + mGameResult + '\'' +
                '}';
    }
    //endregion
}
