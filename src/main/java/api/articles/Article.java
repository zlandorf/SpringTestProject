package api.articles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;


public class Article {
    interface ListView {}

    @JsonView(ListView.class)
    private long id;
    @JsonView(ListView.class)
    private String title;
    @JsonView(ListView.class)
    private String description;
    @JsonView(ListView.class)
    private int countComments;

    private List<Comment> comments;

    public Article(long id, String title, String description, int countComments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.countComments = countComments;
        this.comments = null;
    }

    @Override
    public String toString() {
        return String.format("Article[id=%d, title='%s', description='%s...', comments count='%d']\n", this.id, this.title, this.description.substring(0,  40), this.countComments);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCountComments() {
        return countComments;
    }

    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
