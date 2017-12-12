package ua.edu.nulp.testyourself.data.datasource;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.model.BestResults;
import ua.edu.nulp.testyourself.model.Result;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface ResultDataSource {

    LiveData<List<Result>> getAllResults();

    LiveData<List<Result>> getResultsForUser(String userName);

    LiveData<List<BestResults>> getTopResults(int limit);

    void deleteResult(Result result);

    void deleteAllResults();

}
