/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.aei.io.turnieje.model.datamodel;

import java.util.Date;

/**
 * Representation of single team in single tournament.
 * 
 * @author Piotr Uhl
 */
public class TeamInTournament { //struct
    public TournamentId tourId;
    public TeamId teamId;
    public Integer groupNr;
    public Date joinDate;
    public Integer points;
    public Boolean eliminated;
}
