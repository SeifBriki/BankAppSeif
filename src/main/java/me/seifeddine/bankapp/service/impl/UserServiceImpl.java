package me.seifeddine.bankapp.service.impl;

import me.seifeddine.bankapp.service.UserService;
import me.seifeddine.bankapp.domaine.User;
import me.seifeddine.bankapp.exception.BadRequestFoundException;
import me.seifeddine.bankapp.exception.NotFoundException;

import java.util.*;

public class UserServiceImpl implements UserService {

    private final Map<Long, User> users = new HashMap<>();
    public long lastID = 1;

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findById(long id) {
        return Optional
                .ofNullable(users.get(id))
                .orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));
    }

    @Override
    public User create(User user) {
        if (user == null) throw new BadRequestFoundException("USER_SHOULD_NOT_NULL");
        user.setId(lastID);
        lastID++;
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(long id, User user) {
        if (user == null) throw new BadRequestFoundException("USER_SHOULD_NOT_NULL");
        if (users.get(id) == null) throw new BadRequestFoundException("USER_NOT_EXIST");
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public void deleteById(long id) {
        this.users.remove(id);
    }


}
