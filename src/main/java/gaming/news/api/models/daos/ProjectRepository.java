package gaming.news.api.models.daos;


import gaming.news.api.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
