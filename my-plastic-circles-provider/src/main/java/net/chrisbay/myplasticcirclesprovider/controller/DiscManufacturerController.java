package net.chrisbay.myplasticcirclesprovider.controller;

import net.chrisbay.myplasticcirclesprovider.exception.ValidationException;
import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import net.chrisbay.myplasticcirclesprovider.service.DiscManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/manufacturer")
public class DiscManufacturerController {

    @Autowired
    DiscManufacturerService discManufacturerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiscManufacturer> getAll() {
        return discManufacturerService.getAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DiscManufacturer getById(@PathVariable Integer id) {
        return discManufacturerService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscManufacturer create(@RequestBody @Valid DiscManufacturer discManufacturer, Errors errors) {

        if (errors.hasErrors()) {
            String errorsDescription = errors.getAllErrors().stream()
                    .map(e -> e.toString()).collect(Collectors.joining("\n"));
            throw new ValidationException(errorsDescription);
        }

        discManufacturerService.save(discManufacturer);
        return discManufacturer;
    }
}
