package ua.edu.nulp.testyourself.data.db.user;

import java.util.List;

import ua.edu.nulp.testyourself.model.Result;
import ua.edu.nulp.testyourself.model.User;

/**
 * TestYourSelf-android
 * Created by Yuriy Bereguliak on 12/12/17.
 */

public interface UserDao {

    List<User> getAllUsers();

    void createUser(User user);

    void deleteUser(User user);
}
