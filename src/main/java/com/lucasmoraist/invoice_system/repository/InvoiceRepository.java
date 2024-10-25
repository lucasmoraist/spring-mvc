package com.lucasmoraist.invoice_system.repository;

import com.lucasmoraist.invoice_system.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("""
            SELECT i
            FROM t_invoce i
            WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :term, '%'))
            OR i.nInvoice LIKE LOWER(CONCAT('%', :term, '%'))
            OR i.cnpj LIKE LOWER(CONCAT('%', :term, '%'))
            """)
    List<Invoice> findByTerm(String term);
}
