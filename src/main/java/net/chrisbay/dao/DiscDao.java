package net.chrisbay.dao;

import net.chrisbay.model.Disc;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DiscDao implements Dao<Disc> {

    private Map<Integer, Disc> discs = new HashMap<>();

//    public DiscDao() {
//        Disc newDisc1 = new Disc("Discraft", "Buzzz", 5, 4, -1, 1);
//        this.save(newDisc1);
//
//        Disc newDisc2 = new Disc("Gateway", "Wizard", 2, 3, 0, 2);
//        this.save(newDisc2);
//
//        Disc newDisc3 = new Disc("Discraft", "Avenger SS", 10, 5, -3, 1);
//        this.save(newDisc3);
//    }

    @Override
    public Optional<Disc> get(Integer id) {
        return Optional.ofNullable(this.discs.get(id));
    }

    @Override
    public Iterable<Disc> getAll() {
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
