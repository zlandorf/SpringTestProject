package api.articles;

public class Article {
	private long id;
	private String title;
	private String description;
	
	public Article(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("Article[id=%d, title='%s', description='%s...']\n", this.id, this.title, this.description.substring(0,  40));
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
}
