package com.lucasmoraist.invoice_system.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record InvoiceRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name,
        @NotBlank(message = "Número da nota é obrigatório")
        String nInvoice,
        @NotBlank(message = "Endereço é obrigatório")
        String address,
        @NotBlank(message = "CNPJ é obrigatório")
        String cnpj,
        @NotBlank(message = "Data é obrigatória")
        String date,
        @NotBlank(message = "Valor é obrigatório")
        String amount
) {
}
