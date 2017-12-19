package ua.edu.nulp.testyourself.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import ua.edu.nulp.testyourself.data.datasource.TasksDataSource;
import ua.edu.nulp.testyourself.model.Choice;
import ua.edu.nulp.testyourself.model.TaskDetails;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 16.12.2017.
 */

public class TestViewModel extends AndroidViewModel {

    private LifecycleOwner mLifecycleOwner;

    public TestViewModel(Application application) {
        super(application);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    //region TestViewModel
    public LiveData<List<TaskDetails>> loadTasks(TasksDataSource tasksDataSource) {
        final MutableLiveData<List<TaskDetails>> liveData = new MutableLiveData<>();
        tasksDataSource.getAllTasks().observe(mLifecycleOwner, new Observer<List<TaskDetails>>() {
            @Override
            public void onChanged(@Nullable List<TaskDetails> taskDetails) {
                liveData.postValue(taskDetails);
            }
        });
        return liveData;
    }

    public void updateSingleItemsSelect(final List<TaskDetails> taskDetails, final int taskId, final int choiceId) {
        for (TaskDetails detail : taskDetails) {
            if (detail.mTask.getTaskId() == taskId) {
                for (int i = 0; i < detail.mChoices.size(); i++) {
                    if (detail.mChoices.get(i).getChoiceId() == choiceId) {
                        detail.mChoices.get(i).setCheck(true);
                    } else {
                        detail.mChoices.get(i).setCheck(false);
                    }
                    detail.setUserGiveAnswer(true);
                }
                break;
            }
        }
    }

    public void updateMultiItemChecked(final List<TaskDetails> taskDetails, final int taskId, final int choiceId, boolean isChecked) {
        for (TaskDetails details : taskDetails) {
            if (taskId == details.mTask.getTaskId()) {
                for (Choice choice : details.mChoices) {
                    if (choice.getChoiceId() == choiceId) {
                        choice.setCheck(isChecked);
                        break;
                    }
                }

                int count = 0;
                for (Choice choice : details.mChoices) {
                    if (!choice.isCheck()) {
                        count++;
                    }
                }

                if (count == details.mChoices.size()) {
                    details.setUserGiveAnswer(false);
                } else {
                    details.setUserGiveAnswer(true);
                }

                break;
            }
        }
    }

    public void updateAnswerItem(List<TaskDetails> taskDetails, int taskId, String answer) {
        for (TaskDetails details : taskDetails) {
            if (taskId == details.mTask.getTaskId()) {
                details.mChoices.get(0).setAnswer(answer);
                details.setUserGiveAnswer(true);
                break;
            }
        }
    }
    //endregion
}
