package net.chrisbay.controller;

import net.chrisbay.dao.Dao;
import net.chrisbay.model.Disc;
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
@RequestMapping("discs")
public class DiscController {

    @Autowired
    Dao<Disc> discDao;

    @GetMapping
    public String displayAllDiscs (Model model) {
        model.addAttribute("pageTitle", "My Discs");
        model.addAttribute("discs", discDao.getAll());
        return "discs/index";
    }

    @GetMapping("new")
    public String displayNewDiscForm (Model model) {
        model.addAttribute("pageTitle", "New Disc");
        model.addAttribute("disc", new Disc());
        return "discs/new";
    }

    @PostMapping("new")
    public String processNewDiscForm (Model model,
                                      @Valid @ModelAttribute Disc disc,
                                      Errors errors) {

        if (errors.hasErrors()) {
            return "discs/new";
        }

        discDao.save(disc);

        return "redirect:";
    }

}
