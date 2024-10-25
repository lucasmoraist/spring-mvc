package com.lucasmoraist.invoice_system.domain.dto;

import com.lucasmoraist.invoice_system.domain.entity.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceResponse(
        Long id,
        String name,
        String nInvoice,
        String address,
        String cnpj,
        LocalDate date,
        BigDecimal amount
) {
    public InvoiceResponse(Invoice invoice) {
        this(
                invoice.getId(),
                invoice.getName(),
                invoice.getNInvoice(),
                invoice.getAddress(),
                invoice.getCnpj(),
                invoice.getDate(),
                invoice.getAmount()
        );
    }
}
