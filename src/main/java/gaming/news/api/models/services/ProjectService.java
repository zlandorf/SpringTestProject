package gaming.news.api.models.services;

import gaming.news.api.models.daos.ProjectRepository;
import gaming.news.api.models.entities.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {
    @Resource
    private ProjectRepository repository;

    public Project create(String name) {
        Project project = new Project();
        project.setName(name);
        repository.save(project);
        return project;
    }

    public List<Project> getAll() {
        return repository.findAll();
    }

    public Project save(Project project) {
        return repository.save(project);
    }
}
