package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Entity(tableName = "Tasks")
public class Task {

    @PrimaryKey()
    @ColumnInfo(name = "task_text")
    private String mTaskName;

    //region Getters and Setters
    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(String taskName) {
        mTaskName = taskName;
    }
    //endregion

    //region Object
    @Override
    public String toString() {
        return "Task{" +
                "mTaskName='" + mTaskName + '\'' +
                '}';
    }
    //endregion
}
