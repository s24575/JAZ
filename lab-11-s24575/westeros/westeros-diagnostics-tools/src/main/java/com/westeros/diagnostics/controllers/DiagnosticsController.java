package com.westeros.diagnostics.controllers;

import com.westeros.diagnostics.runners.DiagnosticsRunner;
import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.service.contract.DiagnosticsResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("diagnostics")
public class DiagnosticsController {
    private final IRunDiagnoses diagnosticsRunner;

    @Autowired
    public DiagnosticsController(DiagnosticsRunner diagnosticsRunner) {
        this.diagnosticsRunner = diagnosticsRunner;
    }

    @GetMapping
    public ResponseEntity<String> checkStatus(){
        return ResponseEntity.ok("ALIVE");
    }

    @GetMapping("/check")
    public ResponseEntity<DiagnosticsResultsDto> checkDiagnostics() {
        DiagnosticsResultsDto diagnosticsResult = new DiagnosticsResultsDto();
        diagnosticsResult.setServiceName(this.getClass().getSimpleName());
        diagnosticsResult.setDiagnostics(diagnosticsRunner.runAll());

        return ResponseEntity.ok(diagnosticsResult);
    }
}
