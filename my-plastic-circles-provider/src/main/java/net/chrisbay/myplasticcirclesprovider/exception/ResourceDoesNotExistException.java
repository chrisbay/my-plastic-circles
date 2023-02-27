package net.chrisbay.myplasticcirclesprovider.exception;

public class ResourceDoesNotExistException extends RuntimeException {

    private String type;
    private Integer id;

    public ResourceDoesNotExistException() {}

    public ResourceDoesNotExistException(String type, Integer id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }
}
