package com.testapp.backend.repository.dao;

import com.testapp.backend.models.Roles;
import com.testapp.backend.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class HibernateUserDaoTest {
    private static final HibernateUserDao hibernateUserDao = new HibernateUserDao();


    @BeforeEach
    void setUp() {
        hibernateUserDao.deleteAll();
    }

    @Test
    void getAll() {
        List<User> userList = new ArrayList<>();
        hibernateUserDao.deleteAll();
        for (int i=10;i<20;i++){
            User user = new User(i, "Ivan"+ i, "IIvan"+i, "1234", Set.of(Roles.Admin));
            userList.add(user);
            hibernateUserDao.saveUser(user);
        }
        System.out.println(userList);
        Assertions.assertEquals(userList,hibernateUserDao.getAll());
    }

    @Test
    void getById() {
        User user = new User(1, "Ivan", "IIvan", "1234", Set.of(Roles.Admin));
        hibernateUserDao.saveUser(user);
        Assertions.assertEquals(user,hibernateUserDao.getById(user.get_id()).orElse(new User()));
    }

    @Test
    void getByName() {
        User user = new User(1, "Work", "Employee", "1234", Set.of(Roles.Admin));
        hibernateUserDao.saveUser(user);
        Assertions.assertEquals(user,hibernateUserDao.getById(user.get_id()).orElse(new User()));
    }

    @Test
    void delete() {
        hibernateUserDao.deleteAll();
        User user = new User(1, "Ivan", "IIvan", "1234", Set.of(Roles.Admin));
        hibernateUserDao.saveUser(user);
        hibernateUserDao.delete(user);
        Assertions.assertEquals(hibernateUserDao.getById(user.get_id()), Optional.empty());
    }

    @Test
    void saveUser() {
        hibernateUserDao.deleteAll();
        User user = new User(1, "Ivan", "IIvan", "1234", Set.of(Roles.Admin));
        hibernateUserDao.saveUser(user);
        Assertions.assertEquals(user,hibernateUserDao.getById(user.get_id()).orElse(new User()));
    }

    @Test
    void deleteAll() {
        hibernateUserDao.deleteAll();
    }

    @Test
    void update() {
        User user = new User(1, "Ivan", "IIvan", "1234", Set.of(Roles.Admin));
        hibernateUserDao.saveUser(user);
        user.setName("Stasyan");
        hibernateUserDao.update(user);
        Assertions.assertEquals(user,hibernateUserDao.getById(user.get_id()).orElse(new User()));
    }


    @AfterAll
    static void after(){
        hibernateUserDao.deleteAll();
    }

}