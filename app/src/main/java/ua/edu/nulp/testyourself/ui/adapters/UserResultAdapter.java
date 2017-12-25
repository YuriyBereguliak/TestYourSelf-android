package ua.edu.nulp.testyourself.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.edu.nulp.testyourself.R;
import ua.edu.nulp.testyourself.databinding.ItemUserResultBinding;
import ua.edu.nulp.testyourself.model.Result;

/**
 * Clickers project
 * Created by Yuriy Bereguliak on 25.12.2017.
 */

public class UserResultAdapter extends RecyclerView.Adapter<UserResultAdapter.ResultViewHolder> {

    private List<Result> mResults = new ArrayList<>();

    public void setResults(List<Result> results) {
        mResults.clear();
        mResults.addAll(results);
        notifyDataSetChanged();
    }

    //region Adapter
    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserResultBinding viewDataBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_result, parent, false);
        return new ResultViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.bind(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }
    //endregion

    //region ViewHolder
    static class ResultViewHolder extends RecyclerView.ViewHolder {

        private ItemUserResultBinding mBinding;

        ResultViewHolder(ItemUserResultBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Result result) {
            mBinding.setUserName(result.getUserName());
            mBinding.setUserResult(result.getGameResult());
        }
    }
    //endregion
}
