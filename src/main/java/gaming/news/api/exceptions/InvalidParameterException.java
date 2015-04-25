package gaming.news.api.exceptions;

/**
 * Created by zlandorf on 25/04/2015.
 */
public class InvalidParameterException extends Exception {
    private static final long serialVersionUID = -535389132394488868L;

    public InvalidParameterException(String message) {
        super(message);
    }
}
