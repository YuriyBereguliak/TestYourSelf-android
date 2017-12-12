package ua.edu.nulp.testyourself.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.core.executor.ThreadExecutor;
import ua.edu.nulp.testyourself.data.datasource.ResultDataSource;
import ua.edu.nulp.testyourself.data.db.result.ResultDao;
import ua.edu.nulp.testyourself.model.BestResults;
import ua.edu.nulp.testyourself.model.Result;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class ResultRepository implements ResultDataSource {

    private ThreadExecutor mThreadExecutor;
    private ResultDao mResultDao;

    public ResultRepository(ThreadExecutor threadExecutor, ResultDao resultDao) {
        mThreadExecutor = threadExecutor;
        mResultDao = resultDao;
    }

    //region ResultDataSource
    @Override
    public LiveData<List<Result>> getAllResults() {
        return mResultDao.getAllResults();
    }

    @Override
    public LiveData<List<Result>> getResultsForUser(String userName) {
        return mResultDao.getResultsForUser(userName);
    }

    @Override
    public LiveData<List<BestResults>> getTopResults(final int limit) {
        return mResultDao.getTopResults(limit);
    }

    @Override
    public void deleteResult(final Result result) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mResultDao.deleteResult(result);
            }
        });
    }

    @Override
    public void deleteAllResults() {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mResultDao.deleteAllResults();
            }
        });
    }
    //endregion
}
