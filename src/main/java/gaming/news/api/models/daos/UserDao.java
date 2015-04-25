package gaming.news.api.models.daos;

import gaming.news.api.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

}
