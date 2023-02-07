package net.chrisbay.controller;

import net.chrisbay.dao.Dao;
import net.chrisbay.model.Disc;
import net.chrisbay.model.DiscManufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("discs")
public class DiscController {

    @Autowired
    Dao<Disc> discDao;

    @Autowired
    Dao<DiscManufacturer> discManufacturerDao;

    @GetMapping
    public String displayAllDiscs (Model model) {
        model.addAttribute("pageTitle", "My Discs");
        model.addAttribute("discs", discDao.getAll());
        return "discs/index";
    }

    @GetMapping("new")
    public String displayNewDiscForm (Model model) {
        model.addAttribute("pageTitle", "New Disc");
        model.addAttribute("manufacturers", discManufacturerDao.getAll());
        model.addAttribute("disc", new Disc());
        return "discs/new";
    }

    @PostMapping("new")
    public String processNewDiscForm (Model model,
                                      @Valid @ModelAttribute Disc disc,
                                      Errors errors,
                                      @RequestParam Integer manufacturerId) {

        if (errors.hasErrors()) {
            model.addAttribute("pageTitle", "New Disc");
            model.addAttribute("manufacturers", discManufacturerDao.getAll());
            return "discs/new";
        }

        DiscManufacturer manufacturer = discManufacturerDao.get(manufacturerId).get();
        disc.setManufacturer(manufacturer);
        discDao.save(disc);

        return "redirect:";
    }

}
