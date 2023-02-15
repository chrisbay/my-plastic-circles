package net.chrisbay.myplasticcirclesprovider.controller;

import net.chrisbay.myplasticcirclesprovider.controller.exception.ResourceDoesNotExist;
import net.chrisbay.myplasticcirclesprovider.controller.exception.ValidationException;
import net.chrisbay.myplasticcirclesprovider.model.Disc;
import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import net.chrisbay.myplasticcirclesprovider.repository.DiscManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/manufacturer")
public class DiscManufacturerController {

    @Autowired
    DiscManufacturerRepository discManufacturerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiscManufacturer> getAll() {
        return discManufacturerRepository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DiscManufacturer getById(@PathVariable Integer id) {

        Optional<DiscManufacturer> optionalDiscManufacturer = discManufacturerRepository.findById(id);

        if (!optionalDiscManufacturer.isPresent()) {
            throw new ResourceDoesNotExist("Disc", id);
        }

        return optionalDiscManufacturer.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscManufacturer create(@RequestBody @Valid DiscManufacturer discManufacturer, Errors errors) {

        if (errors.hasErrors()) {
            String errorsDescription = errors.getAllErrors().stream()
                    .map(e -> e.toString()).collect(Collectors.joining("\n"));
            throw new ValidationException(errorsDescription);
        }

        return discManufacturerRepository.save(discManufacturer);
    }
}
