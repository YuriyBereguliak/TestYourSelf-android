package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class TaskDetails {

    @Embedded
    @SerializedName("task")
    public Task mTask;

    @Relation(parentColumn = "task_id", entityColumn = "task_id", entity = Choice.class)
    @SerializedName("choices")
    public List<Choice> mChoices;

    @Ignore
    @Expose
    private boolean mIsUserGiveAnswer;

    //region Getter and Setter
    public boolean isUserGiveAnswer() {
        return mIsUserGiveAnswer;
    }

    public void setUserGiveAnswer(boolean userGiveAnswer) {
        mIsUserGiveAnswer = userGiveAnswer;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "TaskDetails{" +
                "mTask=" + mTask +
                ", mChoices=" + mChoices + "\n" +
                '}';
    }
    //endregion
}
