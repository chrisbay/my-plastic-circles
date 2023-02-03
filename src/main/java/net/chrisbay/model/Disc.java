package net.chrisbay.model;

import java.util.Objects;

public class Disc {

    private final Integer id;
    private static Integer nextId = 1;

    private String model;
    private String manufacturer;
    private Integer speed;
    private Integer glide;
    private Integer turn;
    private Integer fade;

    private Disc () {
        this.id = nextId;
        this.nextId++;
    }

    public Disc(String model, String manufacturer, Integer speed, Integer glide, Integer turn, Integer fade) {
        this();
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

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disc disc = (Disc) o;
        return id.equals(disc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
