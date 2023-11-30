package be.intecbrussel.projectmanagerbackend.exceptions;

public class ProjectManagerException extends RuntimeException {

    public ProjectManagerException() {
        super();
    }

    public ProjectManagerException(String message) {
        super(message);
    }

    public ProjectManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectManagerException(Throwable cause) {
        super(cause);
    }

    protected ProjectManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
