package com.westeros.diagnostics;

import com.westeros.data.repositories.ICatalogData;
import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.service.contract.Diagnostics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseConnectivityDiagnostics implements IDiagnose
{
    private final ICatalogData database;
    @Autowired
    private IRunDiagnoses diagnosticsRunner;

    public DatabaseConnectivityDiagnostics(ICatalogData database, IRunDiagnoses diagnosticsRunner) {
        this.database = database;
        this.diagnosticsRunner = diagnosticsRunner;
        diagnosticsRunner.addDiagnostic(this);
    }

    public String getName(){
        return this.getClass().getSimpleName();
    }
    public String getDescription(){
        return "Checks if database is connected.";
    }
    public Diagnostics run(){
        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setSuccess(true);
        diagnostics.setName(getName());
        diagnostics.setErrorMessage(null);
        diagnostics.setDescription(getDescription());

        try{
            database.getMovies().testQuery();
        } catch(Exception e){
            diagnostics.setSuccess(false);
            diagnostics.setErrorMessage(e.getMessage());
        }

        return diagnostics;
    }
}