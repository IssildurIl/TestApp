package com.testapp.backend.repository.interfaces;

import com.testapp.backend.models.User;
import com.testapp.backend.utils.Outcomes;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAll();

    Optional<User> getById(long id);

    Optional<User> getByName (String name);

    Outcomes delete(User user);

    Outcomes deleteAll();

    Outcomes saveUser(User user);

    Outcomes update(User user);
}
