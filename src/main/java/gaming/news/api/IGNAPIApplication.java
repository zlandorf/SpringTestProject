package gaming.news.api;

import gaming.news.api.models.daos.UserDao;
import gaming.news.api.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IGNAPIApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IGNAPIApplication.class, args);
    }

    @Autowired
    UserDao userDao;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User();
        u1.setFirstname("first name");
        u1.setUsername("zlandorf");

        User u2 = new User();
        u2.setFirstname("burt maklane");
        u2.setUsername("bouya");

        userDao.save(u1);
        userDao.save(u2);

    }
}
