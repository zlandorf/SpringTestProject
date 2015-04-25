package gaming.news.api.exceptions;

public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = -535389132394488869L;

    public ResourceNotFoundException(String resource) {
        super(String.format("Resource not found : %s", resource));
    }
}
