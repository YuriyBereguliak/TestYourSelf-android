package ua.edu.nulp.testyourself.data.db.result;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ua.edu.nulp.testyourself.model.BestResults;
import ua.edu.nulp.testyourself.model.Result;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface ResultDao {

    LiveData<List<Result>> getAllResults();

    LiveData<List<Result>> getResultsForUser(int userId);

    LiveData<List<BestResults>> getTopResults(int limit);

    void deleteResult(Result result);

    void deleteAllResults();
}
