package ua.edu.nulp.testyourself.ui.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.databinding.ItemCheckBoxBinding;
import ua.edu.nulp.testyourself.databinding.ItemMultiQuestionsBinding;
import ua.edu.nulp.testyourself.databinding.ItemSingleQuestionBinding;
import ua.edu.nulp.testyourself.databinding.ItemWriteQuestionBinding;
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
    private OnMultiChoiceSelected mOnMultiChoiceSelected;
    private onSubmitAnswerHandler mOnSubmitAnswerHandler;

    public void setOnSubmitAnswerHandler(onSubmitAnswerHandler onSubmitAnswerHandler) {
        mOnSubmitAnswerHandler = onSubmitAnswerHandler;
    }

    public void setOnSingleChoiceSelected(OnSingleChoiceSelected onSingleChoiceSelected) {
        mOnSingleChoiceSelected = onSingleChoiceSelected;
    }

    public void setOnMultiChoiceSelected(OnMultiChoiceSelected onMultiChoiceSelected) {
        mOnMultiChoiceSelected = onMultiChoiceSelected;
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
            case QuestionType.MULTI:
                return new TestMultipleViewHolder((ItemMultiQuestionsBinding) getData(parent, R.layout.item_multi_questions));
            case QuestionType.WRITE:
                return new WriteAnswerViewHolder((ItemWriteQuestionBinding) getData(parent, R.layout.item_write_question));
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
            case QuestionType.MULTI:
                ((TestMultipleViewHolder) holder).bind(mTaskDetails.get(position), mOnMultiChoiceSelected);
                break;
            case QuestionType.WRITE:
                ((WriteAnswerViewHolder) holder).bind(mTaskDetails.get(position), mOnSubmitAnswerHandler);
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
                        R.color.colorTextPrimary));
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

        @BindView(R.id.linearlayout_item_multi_question_container)
        LinearLayout mCheckBoxLinearLayout;

        private ItemMultiQuestionsBinding mBinding;

        TestMultipleViewHolder(ItemMultiQuestionsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            ButterKnife.bind(this, mBinding.getRoot());
        }

        void bind(final TaskDetails details, final OnMultiChoiceSelected onMultiChoiceSelected) {
            mBinding.setTask(details.mTask);

            mCheckBoxLinearLayout.removeAllViews();
            for (final Choice choice : details.mChoices) {

                ItemCheckBoxBinding checkBoxBinding = DataBindingUtil.inflate(LayoutInflater.from(mBinding.getRoot().getContext()),
                        R.layout.item_check_box, mCheckBoxLinearLayout, false);
                checkBoxBinding.setChoice(choice);

                ((AppCompatCheckBox) checkBoxBinding.getRoot().findViewById(R.id.checkbox_item_check_box))
                        .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                onMultiChoiceSelected.onMultiChoice(details.mTask.getTaskId(), choice.getChoiceId(), isChecked);
                            }
                        });
                mCheckBoxLinearLayout.addView(checkBoxBinding.getRoot());
            }
        }
    }

    static class WriteAnswerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_item_write_questions_submit)
        TextView mSubmitTextView;

        @BindView(R.id.textinputedittext_item_write_question)
        TextInputEditText mAnswerTextInputEditText;

        @BindView(R.id.textinputlayout_item_write_question)
        TextInputLayout mAnswerTextInputLayout;

        private ItemWriteQuestionBinding mBinding;

        WriteAnswerViewHolder(ItemWriteQuestionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            ButterKnife.bind(this, mBinding.getRoot());
        }

        void bind(final TaskDetails details, final onSubmitAnswerHandler onSubmitAnswerHandler) {
            mBinding.setTask(details.mTask);
            mBinding.setAnswer(details.mChoices.get(0).getAnswer());

            mSubmitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(mAnswerTextInputEditText.getText())) {
                        mAnswerTextInputLayout.setError(mBinding.getRoot().getContext()
                                .getString(R.string.text_fragment_test_answer_can_not_be_emprt));
                        return;
                    }
                    mAnswerTextInputLayout.setError("");

                    onSubmitAnswerHandler.onSubmitAnswer(details.mTask.getTaskId(),
                            mAnswerTextInputEditText.getText().toString());
                }
            });
        }
    }
    //endregion

    //region Utility structures
    public interface OnSingleChoiceSelected {
        void onSingleChoice(int taskId, int choiceId);
    }

    public interface OnMultiChoiceSelected {
        void onMultiChoice(int taskId, int choiceId, boolean isChecked);
    }

    public interface onSubmitAnswerHandler {
        void onSubmitAnswer(int taskId, String answer);
    }
    //endregion
}
