package net.chrisbay.controller;

import net.chrisbay.dao.DiscManufacturerDao;
import net.chrisbay.model.DiscManufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("manufacturers")
public class DiscManufacturerController {

    @Autowired
    private DiscManufacturerDao discManufacturerDao;

    @GetMapping
    public String displayAllManufacturers (Model model) {
        model.addAttribute("pageTitle", "All Manufacturers");
        model.addAttribute("manufacturers", discManufacturerDao.getAll());
        return "manufacturers/index";
    }

    @GetMapping("new")
    public String displayNewManufacturerForm (Model model) {
        model.addAttribute("pageTitle", "New Manufacturer");
        model.addAttribute("discManufacturer", new DiscManufacturer());
        return "manufacturers/new";
    }

    @PostMapping("new")
    public String processNewManufacturerForm (Model model,
                                              @Valid @ModelAttribute DiscManufacturer discManufacturer,
                                              Errors errors) {

        if (errors.hasErrors()) {
            return "manufacturers/new";
        }

        discManufacturerDao.save(discManufacturer);

        return "redirect:";
    }

}
