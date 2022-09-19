package main.service;

import main.model.User;
import main.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }
    @Override
    public User getUserById(long id) {
        return this.dao.getUserById(id);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        this.dao.updateUser(user);
    }
    @Transactional
    @Override
    public void deleteUserById(long id) {
        this.dao.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return this.dao.getAllUsers();
    }

}
