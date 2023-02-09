package net.chrisbay.dao;

import net.chrisbay.model.Disc;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DiscDao implements Dao<Disc> {

    private Map<Integer, Disc> discs = new HashMap<>();

    @Override
    public Optional<Disc> get(Integer id) {
        return Optional.ofNullable(this.discs.get(id));
    }

    @Override
    public Collection<Disc> getAll() {
        return this.discs.values();
    }

    @Override
    public void save(Disc disc) {
        this.discs.put(disc.getId(), disc);
    }

    @Override
    public void delete(Disc disc) {
        this.discs.remove(disc.getId());
    }
}
