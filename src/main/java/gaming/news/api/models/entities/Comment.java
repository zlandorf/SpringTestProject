package gaming.news.api.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 10, max = 256, message = "The comment must be between {min} and {max} characters long.")
    private String text;

    public Comment() {
    }

    @Override
    public String toString() {
        return String.format("Comment[id=%d, text='%s...']\n", getId(), getText());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
