package ua.edu.nulp.testyourself.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public class BestResults {

    @Embedded
    private User mUser;

    @Relation(parentColumn = "user_name", entityColumn = "user_name")
    private List<Result> mResults;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }
}
