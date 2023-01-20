package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.service.contract.Diagnostics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DiskSpaceDiagnostics implements IDiagnose {
    @Autowired
    private IRunDiagnoses diagnosticsRunner;

    public DiskSpaceDiagnostics(IRunDiagnoses diagnosticsRunner){
        this.diagnosticsRunner = diagnosticsRunner;
        diagnosticsRunner.addDiagnostic(this);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDescription() {
        return "Checks if the disk has at least 5% free space.";
    }

    @Override
    public Diagnostics run() {
        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setSuccess(true);
        diagnostics.setName(getName());
        diagnostics.setErrorMessage(null);
        diagnostics.setDescription(getDescription());

        File file = new File("/");

        double spacePercentageLeftOnDisk = ((double)file.getFreeSpace() / (double)file.getTotalSpace());

        if(spacePercentageLeftOnDisk < 0.05){
            diagnostics.setSuccess(false);
            String errorMessage = String.format("%.2f%% of total space left on disk", spacePercentageLeftOnDisk * 100.0);
            diagnostics.setErrorMessage(errorMessage);
        }

        return diagnostics;
    }
}
