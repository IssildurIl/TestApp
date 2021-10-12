package com.testapp.backend.rest.api;

import com.testapp.backend.models.User;
import com.testapp.backend.repository.dao.HibernateUserDao;
import com.testapp.backend.utils.Outcomes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employee_controller")
public class UserController {
    private HibernateUserDao hibernateUserDao;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAll() {
        return hibernateUserDao.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id_user")
    public Optional<User> getById(@RequestParam long id) {
        return hibernateUserDao.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name_user")
    public Optional<User> getByName(@RequestParam String name) {
        return hibernateUserDao.getByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_user")
    public Outcomes saveEmployee(@RequestBody User user) {
        return hibernateUserDao.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/upd_user")
    public Outcomes update(@RequestBody User user){
        return hibernateUserDao.update(user);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/delete_user")
    public Outcomes deleteEmployee(@RequestBody User user){
        return hibernateUserDao.delete(user);
    }
}
