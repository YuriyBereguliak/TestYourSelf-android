package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private int mTaskId;

    @ColumnInfo(name = "task_text")
    private String mTaskName;

    @Relation(parentColumn = "task_id", entityColumn = "task_id")
    private List<Choice> mChoices;

    //region Getters and Setters
    public int getTaskId() {
        return mTaskId;
    }

    public void setTaskId(int taskId) {
        mTaskId = taskId;
    }

    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public List<Choice> getChoices() {
        return mChoices;
    }

    public void setChoices(List<Choice> choices) {
        mChoices = choices;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "Task{" +
                "mTaskId=" + mTaskId +
                ", mTaskName='" + mTaskName + '\'' +
                ", mChoices=" + mChoices +
                '}';
    }
    //endregion
}
