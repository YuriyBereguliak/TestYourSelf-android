package ua.edu.nulp.testyourself.data.db.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.Nullable;

import java.util.List;

import ua.edu.nulp.testyourself.model.Result;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

@Dao
public interface UserDaoImpl extends UserDao{

    @Override
    @Nullable
    @Query("SELECT * FROM Users")
    List<User> getAllUsers();

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createUser(User user);

    @Override
    @Delete
    void deleteUser(User user);
}
