package com.westeros.diagnostics;

import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.service.contract.Diagnostics;
import com.westeros.moviesclient.IMoviesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheMovieDbApiConnectivityDiagnostics implements IDiagnose
{
    private final IMoviesClient moviesClient;
    @Autowired
    private IRunDiagnoses diagnosticsRunner;
    public TheMovieDbApiConnectivityDiagnostics(IMoviesClient moviesClient, IRunDiagnoses diagnosticsRunner){
        this.moviesClient = moviesClient;
        this.diagnosticsRunner = diagnosticsRunner;
        diagnosticsRunner.addDiagnostic(this);
    }

    public String getName(){
        return this.getClass().getSimpleName();
    }
    public String getDescription(){
        return "Checks MovieDB api connection.";
    }
    public Diagnostics run(){
        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setSuccess(true);
        diagnostics.setName(getName());
        diagnostics.setErrorMessage(null);
        diagnostics.setDescription(getDescription());

        try{
            moviesClient.getMovie(2);
        } catch(Exception e){
            diagnostics.setSuccess(false);
            diagnostics.setErrorMessage(e.getMessage());
        }

        return diagnostics;
    }
}
