package com.jasper.certificados.service;

import com.jasper.certificados.model.Certificate;
import com.jasper.certificados.repository.CertificateRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepo;

    public CertificateService(CertificateRepository certificateRepo) {
        this.certificateRepo = certificateRepo;
    }

    public byte[] generateCertificate(Long id) {
        Certificate cert = certificateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));

        // Parâmetros para o Jasper
        Map<String, Object> params = new HashMap<>();
        params.put("participant_name", cert.getParticipant().getName());
        params.put("event_name", cert.getEvent().getName());
        params.put("event_date", cert.getEvent().getEventDate().toString());
        params.put("hours", cert.getEvent().getHours());
        params.put("certificate_id", cert.getId().toString());

        try (InputStream jrxmlStream = getClass().getResourceAsStream("/reports/certificate_template.jrxml")) {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar certificado", e);
        }
    }
}

