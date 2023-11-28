package be.intecbrussel.projectmanagerbackend.exceptions;

public class DataNotFoundException extends ProjectManagerException {
    public DataNotFoundException(String dataType, String identifierName, String identifierValue) {
        super("%s with %s: '%s' was not found in database".formatted(
                dataType, identifierName, identifierValue));
    }

}
