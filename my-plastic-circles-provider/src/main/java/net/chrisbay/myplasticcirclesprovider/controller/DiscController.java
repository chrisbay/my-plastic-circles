package net.chrisbay.myplasticcirclesprovider.controller;

import net.chrisbay.myplasticcirclesprovider.exception.ResourceDoesNotExistException;
import net.chrisbay.myplasticcirclesprovider.exception.ValidationException;
import net.chrisbay.myplasticcirclesprovider.model.Disc;
import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import net.chrisbay.myplasticcirclesprovider.repository.DiscManufacturerRepository;
import net.chrisbay.myplasticcirclesprovider.service.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/disc")
public class DiscController {

    @Autowired
    DiscService discService;

    @Autowired
    DiscManufacturerRepository discManufacturerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Disc> getAll() {
        return discService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disc create(@RequestBody @Valid Disc disc, Errors errors) {
        validateDisc(disc, errors);
        discService.save(disc);
        return disc;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Disc getById(@PathVariable Integer id) {
        return discService.get(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Disc updateDisc(@PathVariable Integer id, @RequestBody @Valid Disc discData, Errors errors) {
        validateDisc(discData, errors);
        Disc disc = discService.get(id);
        Disc.mergeDiscs(discData, disc);
        discService.save(disc);
        return disc;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDisc(@PathVariable Integer id) {
        discService.delete(id);
    }

    private void validateDisc (Disc disc, Errors errors) {
        if (errors.hasErrors()) {
            String errorsDescription = errors.getAllErrors().stream()
                    .map(e -> e.toString()).collect(Collectors.joining("\n"));
            throw new ValidationException(errorsDescription);
        }

        Optional<DiscManufacturer> manufacturerOptional = discManufacturerRepository.findById(disc.getManufacturer().getId());

        if (!manufacturerOptional.isPresent()) {
            throw new ResourceDoesNotExistException("DiscManufacturer", disc.getManufacturer().getId());
        }
    }
}
