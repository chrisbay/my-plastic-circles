package net.chrisbay.controller;

import net.chrisbay.dao.Dao;
import net.chrisbay.model.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("discs")
public class DiscController {

    @Autowired
    Dao<Disc> discDao;

    @GetMapping
    public String displayAllDiscs (Model model) {
        model.addAttribute("discs", discDao.getAll());
        return "discs/index";
    }

}
