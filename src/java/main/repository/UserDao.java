package main.repository;

import java.util.List;

import main.model.User;

public interface UserDao {
    void saveUser(User user);


    User getUserById(long id);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteUserById(long id);
}
