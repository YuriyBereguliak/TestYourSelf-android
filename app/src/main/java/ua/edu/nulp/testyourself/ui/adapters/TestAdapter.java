package ua.edu.nulp.testyourself.ui.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.databinding.ItemMultiQuestionsBinding;
import ua.edu.nulp.testyourself.databinding.ItemSingleQuestionBinding;
import ua.edu.nulp.testyourself.model.Choice;
import ua.edu.nulp.testyourself.model.TaskDetails;
import ua.edu.nulp.testyourself.model.defs.QuestionType;
import ua.edu.nulp.testyourself.utils.L;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 17.12.2017.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TaskDetails> mTaskDetails = new ArrayList<>();

    private OnSingleChoiceSelected mOnSingleChoiceSelected;

    public void setOnSingleChoiceSelected(OnSingleChoiceSelected onSingleChoiceSelected) {
        mOnSingleChoiceSelected = onSingleChoiceSelected;
    }

    public List<TaskDetails> getTaskDetails() {
        return mTaskDetails;
    }

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
                return new TestMultipleViewHolder((ItemMultiQuestionsBinding) getData(parent, R.layout.item_multi_questions));
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
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position), mOnSingleChoiceSelected);
                break;
            case QuestionType.INSERT:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position), mOnSingleChoiceSelected);
                break;
            case QuestionType.MULTI:
                ((TestMultipleViewHolder) holder).bind(mTaskDetails.get(position));
                break;
            case QuestionType.TRANSLATE:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position), mOnSingleChoiceSelected);
                break;
            case QuestionType.WRITE:
                ((TestSingleViewHolder) holder).bind(mTaskDetails.get(position), mOnSingleChoiceSelected);
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
        return mTaskDetails.get(position).mTask.getTaskType();
    }
    //endregion

    //region Utility API
    private ViewDataBinding getData(ViewGroup parent, int layout) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false);
    }
    //endregion

    //region ViewHolders
    static class TestSingleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.radiogroup_item_single_question_choices)
        RadioGroup mRadioGroup;

        private ItemSingleQuestionBinding mBinding;

        TestSingleViewHolder(ItemSingleQuestionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            ButterKnife.bind(this, mBinding.getRoot());
        }

        void bind(final TaskDetails details, final OnSingleChoiceSelected onSingleChoiceSelected) {
            mBinding.setTask(details.mTask);

            if (details.mChoices.isEmpty()) {
                L.e("Choices list is empty");
                return;
            }

            RadioGroup radioGroup = new RadioGroup(mBinding.getRoot().getContext());
            for (Choice choice : details.mChoices) {
                AppCompatRadioButton radioButton = new AppCompatRadioButton(
                        mBinding.getRoot().getContext());
                radioButton.setId(choice.getChoiceId());
                radioButton.setText(choice.getChoiceText());
                radioButton.setChecked(choice.isCheck());
                radioButton.setTextColor(ContextCompat.getColor(mBinding.getRoot().getContext(),
                        R.color.colorTextPrimaryLight));
                radioGroup.addView(radioButton);

            }
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                    onSingleChoiceSelected.onSingleChoice(details.mTask.getTaskId(), i);
                }
            });

            mRadioGroup.removeAllViews();
            mRadioGroup.addView(radioGroup);
        }
    }

    static class TestMultipleViewHolder extends RecyclerView.ViewHolder {

        private ItemMultiQuestionsBinding mBinding;

        TestMultipleViewHolder(ItemMultiQuestionsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(TaskDetails details) {
            mBinding.setTask(details.mTask);

        }
    }
    //endregion

    //region Utility structures
    public interface OnSingleChoiceSelected {
        void onSingleChoice(int taskId, int position);
    }
    //endregion
}
