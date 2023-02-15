package net.chrisbay.myplasticcirclesprovider.controller;

import net.chrisbay.myplasticcirclesprovider.controller.exception.ResourceDoesNotExist;
import net.chrisbay.myplasticcirclesprovider.controller.exception.ValidationException;
import net.chrisbay.myplasticcirclesprovider.model.Disc;
import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import net.chrisbay.myplasticcirclesprovider.repository.DiscManufacturerRepository;
import net.chrisbay.myplasticcirclesprovider.repository.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/disc")
public class DiscController {

    @Autowired
    DiscRepository discRepository;

    @Autowired
    DiscManufacturerRepository discManufacturerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disc> getAll() {
        return discRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disc create(@RequestBody @Valid Disc disc, Errors errors) {

        validateDisc(disc, errors);

        return discRepository.save(disc);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Disc getById(@PathVariable Integer id) {

        Optional<Disc> optionalDisc = discRepository.findById(id);

        if (!optionalDisc.isPresent()) {
            throw new ResourceDoesNotExist("Disc", id);
        }

        return optionalDisc.get();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Disc updateDisc(@PathVariable Integer id, @RequestBody @Valid Disc discData, Errors errors) {
        Optional<Disc> optionalDisc = discRepository.findById(id);

        if (!optionalDisc.isPresent()) {
            throw new ResourceDoesNotExist("Disc", id);
        }

        validateDisc(discData, errors);

        Disc disc = optionalDisc.get();
        Disc.mergeDiscs(discData, disc);
        return discRepository.save(disc);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDisc(@PathVariable Integer id) {

        Optional<Disc> optionalDisc = discRepository.findById(id);

        if (!optionalDisc.isPresent()) {
            throw new ResourceDoesNotExist("Disc", id);
        }

        discRepository.deleteById(id);
    }

    private void validateDisc (Disc disc, Errors errors) {
        if (errors.hasErrors()) {
            String errorsDescription = errors.getAllErrors().stream()
                    .map(e -> e.toString()).collect(Collectors.joining("\n"));
            throw new ValidationException(errorsDescription);
        }

        Optional<DiscManufacturer> manufacturerOptional = discManufacturerRepository.findById(disc.getManufacturer().getId());

        if (!manufacturerOptional.isPresent()) {
            throw new ResourceDoesNotExist("DiscManufacturer", disc.getManufacturer().getId());
        }
    }
}
