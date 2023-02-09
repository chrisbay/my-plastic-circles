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

    @GetMapping("new")
    public String displayNewDiscForm (Model model) {
        model.addAttribute("pageTitle", "New Disc");
        model.addAttribute("manufacturers", discManufacturerService.getAll());
        model.addAttribute("disc", new Disc());
        return "discs/new";
    }

    @PostMapping("new")
    public String processNewDiscForm (Model model,
                                      @Valid @ModelAttribute Disc disc,
                                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("pageTitle", "New Disc");
            model.addAttribute("manufacturers", discManufacturerService.getAll());
            return "discs/new";
        }

        discService.save(disc);

        return "redirect:";
    }

    @InitBinder
    protected void InitBinder (WebDataBinder binder) {
        binder.registerCustomEditor(DiscManufacturer.class, new DiscManufacturerEditor(discManufacturerService));
    }

}
