package com.lucasmoraist.invoice_system.controller;

import com.lucasmoraist.invoice_system.domain.dto.InvoiceRequest;
import com.lucasmoraist.invoice_system.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class InvoceController {

    @Autowired
    private InvoiceService service;

    @GetMapping("details/{id}")
    public String show(Model model, @PathVariable Long id) {
        var invoice = this.service.findById(id);
        model.addAttribute("invoice", invoice);
        return "invoice/details";
    }

    @GetMapping("save")
    public String index(Model model, InvoiceRequest invoiceRequest) {
        return "invoice/create";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Long id) {

        var invoice = this.service.findById(id);

        model.addAttribute("invoice", invoice);

        return "invoice/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/";
    }

    @PostMapping("save")
    public String save(@Valid InvoiceRequest invoiceRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "invoice/create";
        }

        this.service.save(invoiceRequest);

        return "redirect:/";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable Long id, @Valid InvoiceRequest invoiceRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "invoice/edit";
        }

        this.service.update(id, invoiceRequest);

        return "redirect:/";
    }

}
