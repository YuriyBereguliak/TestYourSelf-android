package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Choices")
public class Choice {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "choice_id")
    private int mChoiceId;

    @ColumnInfo(name = "choice_text")
    private String mChoiceText;

    @ColumnInfo(name = "choice_is_true")
    private boolean mIsChoiceTrue;

    @ColumnInfo(name = "task_id")
    private int mTaskId;

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

    public boolean isChoiceTrue() {
        return mIsChoiceTrue;
    }

    public void setIsChoiceTrue(boolean choiceTrue) {
        mIsChoiceTrue = choiceTrue;
    }

    public int getTaskId() {
        return mTaskId;
    }

    public void setTaskId(int taskId) {
        mTaskId = taskId;
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
                '}';
    }
    //endregion
}
