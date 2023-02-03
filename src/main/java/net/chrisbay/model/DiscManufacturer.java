package net.chrisbay.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
