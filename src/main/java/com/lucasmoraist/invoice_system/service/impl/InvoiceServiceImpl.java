package com.lucasmoraist.invoice_system.service.impl;

import com.lucasmoraist.invoice_system.domain.dto.InvoiceRequest;
import com.lucasmoraist.invoice_system.domain.dto.InvoiceResponse;
import com.lucasmoraist.invoice_system.domain.entity.Invoice;
import com.lucasmoraist.invoice_system.repository.InvoiceRepository;
import com.lucasmoraist.invoice_system.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Override
    public InvoiceResponse save(InvoiceRequest request) {
        log.info("Saving invoce: {}", request);

        var invoce = new Invoice(request);

        this.repository.save(invoce);

        return new InvoiceResponse(invoce);
    }

    @Override
    public Page<InvoiceResponse> listAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(InvoiceResponse::new);
    }

    @Override
    public List<InvoiceResponse> findByTerm(String term) {
        return this.repository.findByTerm(term)
                .stream()
                .map(InvoiceResponse::new)
                .toList();
    }

    @Override
    public InvoiceResponse update(Long id, InvoiceRequest request) {
        var invoce = this.findById(id);

        invoce.update(request);

        this.repository.save(invoce);

        return new InvoiceResponse(invoce);
    }

    @Override
    public Long delete(Long id) {
        var invoce = this.findById(id);
        this.repository.delete(invoce);
        return id;
    }

    @Override
    public Invoice findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }


}
