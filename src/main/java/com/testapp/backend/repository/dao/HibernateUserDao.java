package com.testapp.backend.repository.dao;

import com.testapp.backend.models.Roles;
import com.testapp.backend.models.User;
import com.testapp.backend.repository.interfaces.UserDao;
import com.testapp.backend.utils.HibernateDataUtil;
import com.testapp.backend.utils.Outcomes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HibernateUserDao implements UserDao {

    private final HibernateDataUtil hibernateDataUtil = new HibernateDataUtil();

    @Override
    public List<User> getAll() {
        return hibernateDataUtil.execQList(User.class,"from User");
    }

    @Override
    public Optional<User> getById(long id) {
        return hibernateDataUtil.getUserById(User.class,id);
    }

    @Override
    public Optional<User> getByName(String name) {
        return hibernateDataUtil.execQSingle(User.class,"from User where name = ?1", name);
    }

    @Override
    public Outcomes delete(User user) {
        return hibernateDataUtil.deleteUser(user.getClass(),user.get_id());
    }

    @Override
    public Outcomes deleteAll() {
        return hibernateDataUtil.deleteAll();
    }

    @Override
    public Outcomes saveUser(User user) {
        return hibernateDataUtil.createUser(user);
    }

    @Override
    public Outcomes update(User user) {
        return hibernateDataUtil.updateUser(user);
    }
}
