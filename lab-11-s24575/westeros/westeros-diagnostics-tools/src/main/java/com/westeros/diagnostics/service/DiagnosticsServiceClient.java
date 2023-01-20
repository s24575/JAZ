package com.westeros.diagnostics.service;

import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.service.contract.DiagnosticsResultsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DiagnosticsServiceClient implements IDiagnosticsServiceClient{
    @Value("${server.port:8080}")
    private String URL;
    private final RestTemplate restTemplate;
    private final IRunDiagnoses diagnosticsRunner;

    public DiagnosticsServiceClient(RestTemplate restTemplate, IRunDiagnoses diagnosticsRunner) {
        this.restTemplate = restTemplate;
        this.diagnosticsRunner = diagnosticsRunner;
    }

    @Override
    public void sendDiagnostics() {
        DiagnosticsResultsDto diagnosticsResultsDto = new DiagnosticsResultsDto();
        diagnosticsResultsDto.setServiceName(this.getClass().getSimpleName());
        diagnosticsResultsDto.setDiagnostics(diagnosticsRunner.runAll());

        HttpEntity<DiagnosticsResultsDto> request = new HttpEntity<>(diagnosticsResultsDto);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + URL + "/check/services", request, String.class);
    }
}
