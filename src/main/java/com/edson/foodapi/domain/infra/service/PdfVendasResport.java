package com.edson.foodapi.domain.infra.service;

import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.filter.VendaDiariaFilter;
import com.edson.foodapi.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@Service
public class PdfVendasResport implements VendaReportService {

    @Autowired
    private VendaQueryServiceImpl vendaQueryService;

    @Override
    public byte[] emitirVedasDiarias(VendaDiariaFilter filtros) {

        try {

            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var vendasDiarias = this.vendaQueryService.consultarVendasDiaria(filtros);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
           throw new BadRequestException("Relatório falhou");
        }
    }
}
