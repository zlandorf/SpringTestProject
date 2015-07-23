package gaming.news.api.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;

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
