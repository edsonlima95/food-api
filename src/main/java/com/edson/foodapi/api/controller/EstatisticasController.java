package com.edson.foodapi.api.controller;


import com.edson.foodapi.domain.filter.VendaDiariaFilter;
import com.edson.foodapi.domain.infra.service.PdfVendasResport;
import com.edson.foodapi.domain.infra.service.VendaQueryServiceImpl;
import com.edson.foodapi.domain.model.dto.VendaDiaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryServiceImpl vendaQueryService;

    @Autowired
    private PdfVendasResport pdfVendasResport;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtros) {
        return this.vendaQueryService.consultarVendasDiaria(filtros);
    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtros) {

        byte[] pdfBytes = this.pdfVendasResport.emitirVedasDiarias(filtros);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(pdfBytes);
    }

}
