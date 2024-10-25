package com.lucasmoraist.invoice_system.service;

import com.lucasmoraist.invoice_system.domain.dto.InvoiceRequest;
import com.lucasmoraist.invoice_system.domain.dto.InvoiceResponse;
import com.lucasmoraist.invoice_system.domain.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    InvoiceResponse save(InvoiceRequest request);
    Page<InvoiceResponse> listAll(Pageable pageable);
    List<InvoiceResponse> findByTerm(String term);
    InvoiceResponse update(Long id, InvoiceRequest request);
    Long delete(Long id);
    Invoice findById(Long id);
}
