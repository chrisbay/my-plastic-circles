package net.chrisbay.model;

import java.util.Objects;

public class Disc extends AbstractEntity {

    private String model;
    private String manufacturer;
    private Integer speed;
    private Integer glide;
    private Integer turn;
    private Integer fade;

    public Disc(String model, String manufacturer, Integer speed, Integer glide, Integer turn, Integer fade) {
        super();
        this.model = model;
        this.manufacturer = manufacturer;
        this.speed = speed;
        this.glide = glide;
        this.turn = turn;
        this.fade = fade;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getGlide() {
        return glide;
    }

    public void setGlide(Integer glide) {
        this.glide = glide;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Integer getFade() {
        return fade;
    }

    public void setFade(Integer fade) {
        this.fade = fade;
    }

}
