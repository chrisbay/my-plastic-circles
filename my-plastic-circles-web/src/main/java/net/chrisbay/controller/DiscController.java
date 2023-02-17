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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            Disc disc = discService.get(id);

            if (disc == null) {
                model.addAttribute("message", "warning|Invalid disc ID");
            } else {
                model.addAttribute("disc", disc);
            }

        }

        model.addAttribute("manufacturers", discManufacturerService.getAll());

        return "discs/edit";
    }

    @PostMapping("edit/{id}")
    public String processEditDiscForm (Model model,
                                       @Valid @ModelAttribute Disc disc,
                                       Errors errors,
                                       @PathVariable Integer id,
                                       RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            model.addAttribute("isNew", id == 0);
            model.addAttribute("manufacturers", discManufacturerService.getAll());
            model.addAttribute("message", "danger|Please correct the validation errors described below");
            return "discs/edit";
        }

        if (id == 0) {
            disc.setId(null);
        }

        discService.save(disc);
        redirectAttributes.addFlashAttribute("message", "success|<b>" + disc.getDisplayName() + "</b> saved!");

        return "redirect:/discs";
    }

    @PostMapping("delete/{id}")
    public String deleteDisc(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Disc disc = discService.get(id);
        discService.delete(id);
        redirectAttributes.addFlashAttribute("message", "success|<b>" + disc.getDisplayName() + "</b> deleted!");
        return "redirect:/discs";
    }

    @InitBinder
    protected void InitBinder (WebDataBinder binder) {
        binder.registerCustomEditor(DiscManufacturer.class, new DiscManufacturerEditor(discManufacturerService));
    }

}
