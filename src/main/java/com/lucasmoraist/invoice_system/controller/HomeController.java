package com.lucasmoraist.invoice_system.controller;

import com.lucasmoraist.invoice_system.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private InvoiceService service;

    @GetMapping("/")
    public String list(Model model, @PageableDefault(value = 10, page = 0) Pageable pageable) {

        var invoice = this.service.listAll(pageable);

        model.addAttribute("invoice", invoice);
        return "home";
    }

    @GetMapping("/find")
    public String showFindPage(Model model) {
        return "home";
    }

    @PostMapping("/find")
    public String find(Model model, @RequestParam(required = false) String term) {
        if (term != null && !term.isEmpty()) {
            model.addAttribute("invoice", this.service.findByTerm(term));
        } else {
            model.addAttribute("error", "Por favor, insira um termo para buscar.");
        }
        return "home";
    }

}
