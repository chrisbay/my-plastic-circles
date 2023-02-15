package net.chrisbay.controller;

import net.chrisbay.model.Disc;
import net.chrisbay.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api")
public class DiscRestController {

    @Autowired
    private EntityService<Disc> discService;

    @GetMapping("disc")
    public ResponseEntity<Disc[]> getAllDiscs () {
        Disc[] discs = discService.getAll();
        if (discs.length == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(discs, HttpStatus.OK);
    }

    @PutMapping("disc/{id}")
    public ResponseEntity<Disc> updateDisc(@PathVariable Integer id, @RequestBody Disc disc) {
        Disc currentDisc = discService.get(id);
        if (currentDisc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        discService.save(disc);
        return new ResponseEntity<>(disc, HttpStatus.OK);
    }



}
