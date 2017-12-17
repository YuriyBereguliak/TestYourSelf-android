package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Choices")
public class Choice {

    @PrimaryKey()
    @ColumnInfo(name = "choice_id")
    @SerializedName("choice_id")
    private int mChoiceId;

    @ColumnInfo(name = "choice_text")
    @SerializedName("choice_text")
    private String mChoiceText;

    @ColumnInfo(name = "choice_is_true")
    @SerializedName("choice_is_true")
    private int mIsChoiceTrue;

    @ColumnInfo(name = "task_id")
    @SerializedName("task_id")
    private int mTaskId;

    @Ignore
    @Expose
    private boolean mIsCheck;

    //region Getters and Setters

    public int getChoiceId() {
        return mChoiceId;
    }

    public void setChoiceId(int choiceId) {
        mChoiceId = choiceId;
    }

    public String getChoiceText() {
        return mChoiceText;
    }

    public void setChoiceText(String choiceText) {
        mChoiceText = choiceText;
    }

    public int getIsChoiceTrue() {
        return mIsChoiceTrue;
    }

    public boolean isChoiceTrue() {
        return mIsChoiceTrue == 1;
    }

    public void setIsChoiceTrue(int choiceTrue) {
        mIsChoiceTrue = choiceTrue;
    }

    public int getTaskId() {
        return mTaskId;
    }

    public void setTaskId(int taskId) {
        mTaskId = taskId;
    }

    public boolean isCheck() {
        return mIsCheck;
    }

    public void setCheck(boolean check) {
        mIsCheck = check;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "Choice{" +
                "mChoiceId=" + mChoiceId +
                ", mChoiceText='" + mChoiceText + '\'' +
                ", mIsChoiceTrue=" + mIsChoiceTrue +
                ", mTaskId=" + mTaskId +
                ", mIsCheck=" + mIsCheck +
                '}';
    }
    //endregion
}
