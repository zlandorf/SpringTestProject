package gaming.news.api.models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentForm {
    private long id = 0;

    @NotNull
    @Size(min = 10, max = 256, message = "The comment must be between {min} and {max} characters long.")
    private String text;

    public CommentForm() {
    }

    public String getText() {
        return text;
    }

    public void setText(String comment) {
        this.text = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
