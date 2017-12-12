package ua.edu.nulp.testyourself.data.db.result;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;
import android.support.annotation.Nullable;

import java.util.List;

import ua.edu.nulp.testyourself.model.BestResults;
import ua.edu.nulp.testyourself.model.Result;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Dao
public interface ResultDaoImpl extends ResultDao {

    @Override
    @Nullable
    @Query("SELECT * FROM Results")
    LiveData<List<Result>> getAllResults();

    @Override
    @Nullable
    @Query("SELECT * FROM Results WHERE user_id= :userId")
    LiveData<List<Result>> getResultsForUser(int userId);

    @Override
    @Nullable
    @Query("SELECT * FROM Users LIMIT :limit ")
    LiveData<List<BestResults>> getTopResults(int limit);

    @Override
    @Delete
    void deleteResult(Result result);

    @Override
    @Query("DELETE FROM Results")
    void deleteAllResults();
}
