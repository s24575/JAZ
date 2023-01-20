package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.service.contract.Diagnostics;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosticsRunner implements IRunDiagnoses {

    private final List<IDiagnose> diagnosticsTests = new ArrayList<>();

    public void addDiagnostic(IDiagnose diagnostic){
        diagnosticsTests.add(diagnostic);
    }

    @Override
    public List<Diagnostics> runAll() {
        List<Diagnostics> diagnostics = new ArrayList<>();

        for(var diagnosticTest : diagnosticsTests){
            diagnostics.add(diagnosticTest.run());
        }

        return diagnostics;
    }
}
