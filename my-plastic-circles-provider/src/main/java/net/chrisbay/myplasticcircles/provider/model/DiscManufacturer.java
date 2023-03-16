package net.chrisbay.myplasticcircles.provider.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class DiscManufacturer extends AbstractEntity {

    @NotNull
    @Size(min = 2, max=50, message = "Manufacturer name must be between 2 and 50 characters")
    private String name;

    public DiscManufacturer () {}

    public DiscManufacturer(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
