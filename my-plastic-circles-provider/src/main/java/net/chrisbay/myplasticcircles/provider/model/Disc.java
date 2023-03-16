package net.chrisbay.myplasticcircles.provider.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Disc extends AbstractEntity {

    @NotNull
    @Size(min=2, max=50, message = "Model must be between 2 and 50 characters")
    private String model;

    @ManyToOne
    @NotNull(message = "Manufacturer is required")
    private DiscManufacturer manufacturer;

    @NotNull(message = "Required")
    @Min(value = 1, message = "Speed must be between 1 and 14")
    @Max(value = 14, message = "Speed must be between 1 and 14")
    private Integer speed;

    @NotNull(message = "Required")
    @Min(value = 1, message = "Glide must be between 1 and 7")
    @Max(value = 7, message = "Glide must be between 1 and 7")
    private Integer glide;

    @NotNull(message = "Required")
    @Min(value = -5, message = "Turn must be between -5 and 1")
    @Max(value = 1, message = "Turn must be between -5 and 1")
    private Integer turn;

    @NotNull(message = "Required")
    @Min(value = 0, message = "Fade must be between 0 and 5")
    @Max(value = 5, message = "Fade must be between 0 and 5")
    private Integer fade;

    private String notes;

    private Boolean isFavorite = false;

    public Disc () {}

    public Disc(String model, DiscManufacturer manufacturer, Integer speed, Integer glide, Integer turn, Integer fade) {
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

    public DiscManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(DiscManufacturer manufacturer) {
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public static Disc mergeDiscs(Disc source, Disc target) {
        target.setModel(source.getModel());
        target.setManufacturer(source.getManufacturer());
        target.setSpeed(source.getSpeed());
        target.setGlide(source.getGlide());
        target.setTurn(source.getTurn());
        target.setFade(source.getFade());
        target.setFavorite(source.getFavorite());
        target.setNotes(source.getNotes());
        return target;
    }
}
