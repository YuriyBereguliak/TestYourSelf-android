package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import ua.edu.nulp.testyourself.model.defs.QuestionType;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Tasks")
public class Task {

    @PrimaryKey
    @ColumnInfo(name = "task_id")
    @SerializedName("task_id")
    private int mTaskId;

    @ColumnInfo(name = "task_text")
    @SerializedName("task_text")
    private String mTaskName;

    @ColumnInfo(name = "task_type")
    @SerializedName("task_type")
    @QuestionType
    private int mTaskType;

    //region Getters and Setters
    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }

    public int getTaskId() {
        return mTaskId;
    }

    public void setTaskId(int taskId) {
        mTaskId = taskId;
    }

    @QuestionType
    public int getTaskType() {
        return mTaskType;
    }

    public void setTaskType(@QuestionType int taskType) {
        mTaskType = taskType;
    }

    //endregion

    //region Object
    @Override
    public String toString() {
        return "Task{" +
                "mTaskId=" + mTaskId +
                ", mTaskName='" + mTaskName + '\'' +
                ", mTaskType=" + mTaskType +
                '}';
    }
    //endregion
}
