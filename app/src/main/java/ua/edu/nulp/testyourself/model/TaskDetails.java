package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class TaskDetails {

    @Embedded
    public Task mTask;

    @Relation(parentColumn = "task_text", entityColumn = "task_id", entity = Choice.class)
    public List<Choice> mChoices;

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
