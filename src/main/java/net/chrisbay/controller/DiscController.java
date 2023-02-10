package net.chrisbay.controller;

import net.chrisbay.controller.bind.DiscManufacturerEditor;
import net.chrisbay.model.Disc;
import net.chrisbay.model.DiscManufacturer;
import net.chrisbay.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("discs")
public class DiscController {

    @Autowired
    EntityService<Disc> discService;

    @Autowired
    private EntityService<DiscManufacturer> discManufacturerService;

    @GetMapping
    public String displayAllDiscs (Model model) {
        model.addAttribute("pageTitle", "My Discs");
        model.addAttribute("discs", discService.getAll());
        return "discs/index";
    }

    @GetMapping("edit/{id}")
    public String displayEditDiscForm (Model model, @PathVariable Integer id) {

        if (id == 0) {
            model.addAttribute("isNew", true);
            model.addAttribute("disc", new Disc());
        } else {
            model.addAttribute("isNew", false);
            model.addAttribute("disc", discService.get(id));
        }

        model.addAttribute("manufacturers", discManufacturerService.getAll());

        return "discs/edit";
    }

    @PostMapping("edit/{id}")
    public String processEditDiscForm (Model model,
                                      @Valid @ModelAttribute Disc disc,
                                      Errors errors,
                                      @PathVariable Integer id) {

        if (errors.hasErrors()) {
            model.addAttribute("isNew", true);
            model.addAttribute("manufacturers", discManufacturerService.getAll());
            return "discs/edit";
        }

        if (id == 0) {
            disc.setId(null);
        }

        discService.save(disc);

        return "redirect:/discs";
    }

    @InitBinder
    protected void InitBinder (WebDataBinder binder) {
        binder.registerCustomEditor(DiscManufacturer.class, new DiscManufacturerEditor(discManufacturerService));
    }

}
