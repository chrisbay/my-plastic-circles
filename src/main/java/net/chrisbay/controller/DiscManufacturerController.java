package net.chrisbay.controller;

import net.chrisbay.dao.DiscManufacturerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "manufacturers/new";
    }

    @PostMapping("new")
    public String processNewManufacturerForm (Model model) {

        return "redirect:";
    }

}
