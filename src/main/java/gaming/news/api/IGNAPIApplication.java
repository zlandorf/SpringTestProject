package gaming.news.api;

import gaming.news.api.models.entities.Project;
import gaming.news.api.models.entities.ProjectResource;
import gaming.news.api.models.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IGNAPIApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IGNAPIApplication.class, args);
    }

    @Autowired
    ProjectService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Adding projects ...");

        Project firstProject = service.create("first project");
        List<ProjectResource> resources = new ArrayList<>();
        ProjectResource paul = new ProjectResource();
        ProjectResource pierre = new ProjectResource();
        paul.setName("paul");
        pierre.setName("pierre");
        resources.add(paul);
        resources.add(pierre);
        firstProject.setResources(resources);
        service.save(firstProject);

        service.create("second project");

        System.out.println("Following projects have been added : ");

        for (Project project : service.getAll()) {
            int resourceCount = project.getResources().size();
            StringBuilder resourceNames = new StringBuilder();
            for (int i = 0; i < resourceCount - 1; i++) {
                resourceNames.append(project.getResources().get(i).getName());
                resourceNames.append(", ");
            }
            if (!project.getResources().isEmpty()) {
                resourceNames.append(project.getResources().get(resourceCount - 1).getName());
            }
            System.out.printf("Project : [%d] %s {%s}\n", project.getId(), project.getName(), resourceNames.toString());
        }
    }
}
