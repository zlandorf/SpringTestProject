package gaming.news.api.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -535389132394488869L;

    private String resource;

    public ResourceNotFoundException(String resource) {
        this.resource = resource;
    }

    @Override
    public String getMessage() {
        return String.format("Resource not found : %s", getResource());
    }

    public String getResource() {
        return this.resource;
    }
}
