package gaming.news.api.models.services;

import gaming.news.api.models.daos.ProjectRepository;
import gaming.news.api.models.entities.Project;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {
    @Resource
    private ProjectRepository repository;

    public Project create(String name) {
        Project project = new Project();
        project.setName(name);
        return repository.save(project);
    }

    @Transactional
    public List<Project> getAll() {
        List<Project> projects = repository.findAll();
        for (Project project: projects) {
            Hibernate.initialize(project.getResources());
        }
        return projects;
    }

    public Project save(Project project) {
        return repository.save(project);
    }
}
