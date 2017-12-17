package ua.edu.nulp.testyourself.ui.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.databinding.ItemSingleQuestionBinding;
import ua.edu.nulp.testyourself.model.TaskDetails;
import ua.edu.nulp.testyourself.model.defs.QuestionType;
import ua.edu.nulp.testyourself.utils.L;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 17.12.2017.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TaskDetails> mTaskDetails = new ArrayList<>();

    public void setTaskDetails(List<TaskDetails> taskDetails) {
        mTaskDetails.clear();
        mTaskDetails.addAll(taskDetails);

        notifyDataSetChanged();
    }

    //region Adapter
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case QuestionType.SINGLE:
                return new TestSingleViewHolder((ItemSingleQuestionBinding) getData(parent, R.layout.item_single_question));
            case QuestionType.INSERT:
                return new TestSingleViewHolder((ItemSingleQuestionBinding) getData(parent, R.layout.item_single_question));
            case QuestionType.MULTI:
                return new TestSingleViewHolder((ItemSingleQuestionBinding) getData(parent, R.layout.item_single_question));
            case QuestionType.TRANSLATE:
                return new TestSingleViewHolder((ItemSingleQuestionBinding) getData(parent, R.layout.item_single_question));
            case QuestionType.WRITE:
                return new TestSingleViewHolder((ItemSingleQuestionBinding) getData(parent, R.layout.item_single_question));
            default:
                L.d("Unsupported item type");
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mTaskDetails.get(position).mTask.getTaskType()) {
            case QuestionType.SINGLE:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position));
                break;
            case QuestionType.INSERT:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position));
                break;
            case QuestionType.MULTI:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position));
                break;
            case QuestionType.TRANSLATE:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position));
                break;
            case QuestionType.WRITE:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position));
                break;
            default:
                L.d("Unsupported item type");
        }
    }

    @Override
    public int getItemCount() {
        return mTaskDetails == null ? 0 : mTaskDetails.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  mTaskDetails.get(position).mTask.getTaskType();
    }
    //endregion

    //region Utility API
    private ViewDataBinding getData(ViewGroup parent, int layout) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false);
    }
    //endregion

    //region ViewHolders
    static class TestSingleViewHolder extends RecyclerView.ViewHolder {

        private ItemSingleQuestionBinding mBinding;

        TestSingleViewHolder(ItemSingleQuestionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(TaskDetails details) {
            mBinding.setTask(details.mTask);

        }
    }

    static class TestMultipleViewHolder extends RecyclerView.ViewHolder {

        private ItemSingleQuestionBinding mBinding;

        TestMultipleViewHolder(ItemSingleQuestionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(TaskDetails details) {
            mBinding.setTask(details.mTask);

        }
    }
    //endregion
}
