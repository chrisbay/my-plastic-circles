package net.chrisbay.myplasticcirclesprovider.controller.exception;

public class ResourceDoesNotExist extends RuntimeException {

    private String type;
    private Integer id;

    public ResourceDoesNotExist() {}

    public ResourceDoesNotExist(String type, Integer id) {
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
