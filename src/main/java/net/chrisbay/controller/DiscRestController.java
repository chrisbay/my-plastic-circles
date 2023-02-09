package net.chrisbay.controller;

import net.chrisbay.dao.DiscDao;
import net.chrisbay.model.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class DiscRestController {

    @Autowired
    private DiscDao discDao;

    @GetMapping("disc")
    public ResponseEntity<Collection<Disc>> getAllDiscs () {
        Collection<Disc> discs = discDao.getAll();
        if (discs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(discs, HttpStatus.OK);
    }

    @PutMapping("disc/{id}")
    public ResponseEntity<Disc> updateDisc(@PathVariable Integer id, @RequestBody Disc disc) {
        Optional<Disc> currentDiscRes = discDao.get(id);
        if (!currentDiscRes.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        discDao.save(disc);
        return new ResponseEntity<>(disc, HttpStatus.OK);
    }

}
