package com.testapp.backend.rest.api;

import com.testapp.backend.models.User;
import com.testapp.backend.repository.dao.HibernateUserDao;
import com.testapp.backend.utils.Outcomes;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@RestController
@RequestMapping("api/employee_controller")
public class UserController {
    private final HibernateUserDao hibernateUserDao = new HibernateUserDao();

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAll(HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().write("success: true");
            return hibernateUserDao.getAll();
        } catch (Exception e) {
            response.getWriter().write("success: false, errors: { " + e + " } ");
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id_user")
    public Optional<User> getById(@RequestParam long id, HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().write("success: true");
            return hibernateUserDao.getById(id);
        } catch (Exception e) {
            response.getWriter().write("success: false, errors: { " + e + " } ");
            return Optional.empty();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/name_user")
    public Optional<User> getByName(@RequestParam String name, HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        Pattern pattern = Pattern.compile("А.+а");
        Matcher matcher = pattern.matcher(name);
        try {
            if (matcher.find()) {
                response.getWriter().write("success: true");
                return hibernateUserDao.getByName(name);
            } else {
                response.getWriter().write("success: false, errors: { Validation false }");
                return Optional.empty();
            }
        } catch (Exception e) {
            response.getWriter().write("success: false, errors: { " + e + " } ");
            return Optional.empty();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save_user")
    public Outcomes saveEmployee(@RequestBody User user, HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().write("success: true");
            return hibernateUserDao.saveUser(user);
        } catch (Exception e) {
            response.getWriter().write("success: false, errors: { " + e + " } ");
            return Outcomes.FAILED;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/upd_user")
    public Outcomes update(@RequestBody User user, HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().write("success: true");
            return hibernateUserDao.update(user);
        } catch (Exception e) {
            response.getWriter().write("success: false, errors: { " + e + " } ");
            return Outcomes.FAILED;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete_user")
    public Outcomes deleteEmployee(@RequestBody User user, HttpServletRequest servletRequest, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().write("success: true");
            return hibernateUserDao.delete(user);
        } catch (Exception e) {
            response.getWriter().write("success: false, errors: { " + e + " } ");
            return Outcomes.FAILED;
        }
    }
}
