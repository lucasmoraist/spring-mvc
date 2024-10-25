package com.lucasmoraist.invoice_system.domain.entity;

import com.lucasmoraist.invoice_system.domain.dto.InvoiceRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_invoce")
@Table(name = "t_invoce")
@EqualsAndHashCode(of = "id")
public class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nInvoice;
    private String address;
    private String cnpj;
    private LocalDate date;
    private BigDecimal amount;

    public Invoice(InvoiceRequest request) {
        this.name = request.name();
        this.nInvoice = request.nInvoice();
        this.address = request.address();
        this.cnpj = request.cnpj();
        this.date = DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(request.date(), LocalDate::from);
        this.amount = parseAmount(request.amount());
    }

    public void update(InvoiceRequest request) {
        if (request.name() != null) {
            this.name = request.name();
        }
        if (request.nInvoice() != null) {
            this.nInvoice = request.nInvoice();
        }
        if (request.address() != null) {
            this.address = request.address();
        }
        if (request.cnpj() != null) {
            this.cnpj = request.cnpj();
        }
        if (request.date() != null) {
            this.date = DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(request.date(), LocalDate::from);
        }
        if (request.amount() != null) {
            this.amount = parseAmount(request.amount());
        }
    }

    private BigDecimal parseAmount(String amountString) {
        String cleanedAmount = amountString.replaceAll("[^\\d,]", "").replace(",", ".");
        return new BigDecimal(cleanedAmount);
    }
}
