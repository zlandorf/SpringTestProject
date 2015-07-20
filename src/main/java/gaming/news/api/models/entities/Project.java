package gaming.news.api.models.entities;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectResource> resources;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProjectResource> getResources() {
        return resources;
    }

    public void setResources(List<ProjectResource> resources) {
        this.resources = resources;
    }
}
