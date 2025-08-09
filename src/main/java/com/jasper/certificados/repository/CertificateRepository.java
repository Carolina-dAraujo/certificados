package com.jasper.certificados.repository;

import com.jasper.certificados.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}

