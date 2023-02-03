package net.chrisbay.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Disc extends AbstractEntity {

    @NotNull
    @Size(min=2, max=50, message = "Model must be between 2 and 50 characters")
    private String model;

    @NotNull
    @Size(min=2, max=50)
    private String manufacturer;

    @Min(value = 1, message = "Speed must be between 1 and 14")
    @Max(value = 14, message = "Speed must be between 1 and 14")
    private Integer speed;

    @Min(value = 1, message = "Glide must be between 1 and 7")
    @Max(value = 7, message = "Glide must be between 1 and 7")
    private Integer glide;

    @Min(value = -5, message = "Turn must be between -5 and 1")
    @Max(value = 1, message = "Turn must be between -5 and 1")
    private Integer turn;

    @Min(value = 0, message = "Fade must be between 0 and 5")
    @Max(value = 5, message = "Fade must be between 0 and 5")
    private Integer fade;

    public Disc () {}

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
