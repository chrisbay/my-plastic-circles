package net.chrisbay.dao;

import net.chrisbay.model.DiscManufacturer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DiscManufacturerDao implements Dao<DiscManufacturer> {

    private Map<Integer, DiscManufacturer> manufacturers = new HashMap<>();

    public DiscManufacturerDao () {
        this.save(new DiscManufacturer("Discraft"));
        this.save(new DiscManufacturer("Gateway"));
        this.save(new DiscManufacturer("Mint"));
    }

    @Override
    public Optional<DiscManufacturer> get(Integer id) {
        return Optional.ofNullable(this.manufacturers.get(id));
    }

    @Override
    public Iterable<DiscManufacturer> getAll() {
        return this.manufacturers.values();
    }

    @Override
    public void save(DiscManufacturer discManufacturer) {
        this.manufacturers.put(discManufacturer.getId(), discManufacturer);
    }

    @Override
    public void delete(DiscManufacturer discManufacturer) {
        this.manufacturers.remove(discManufacturer.getId());
    }
}
