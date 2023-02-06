package net.chrisbay.controller;

import net.chrisbay.dao.DiscDao;
import net.chrisbay.model.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

}
