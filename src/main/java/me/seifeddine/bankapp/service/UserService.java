package me.seifeddine.bankapp.service;

import me.seifeddine.bankapp.domaine.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    User create(User user);

    User update(long id, User user);

    void deleteById(long id);

}
