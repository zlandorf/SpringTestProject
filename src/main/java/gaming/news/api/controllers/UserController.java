package gaming.news.api.controllers;

import gaming.news.api.models.daos.UserDao;
import gaming.news.api.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/users")
    public List<User> users() {
        return userDao.findAll();
    }
}
