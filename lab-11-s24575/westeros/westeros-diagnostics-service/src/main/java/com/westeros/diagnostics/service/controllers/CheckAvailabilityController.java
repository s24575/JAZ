package com.westeros.diagnostics.service.controllers;


import com.westeros.diagnostics.service.contract.DiagnosticsResultsDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/check")
public class CheckAvailabilityController {
    DiagnosticsResultsDto diagnostics;

    public CheckAvailabilityController() {
        System.out.println("CREATED CHECK");
    }

    @GetMapping("/services")
    @ResponseBody
    public DiagnosticsResultsDto checkDiagnostics(){
        System.out.println("GET");
        return diagnostics;
    }

    @PostMapping("/services")
    public void checkDiagnostics(@RequestBody DiagnosticsResultsDto diagnostics){
        System.out.println("POST");
        this.diagnostics = diagnostics;
    }
}
